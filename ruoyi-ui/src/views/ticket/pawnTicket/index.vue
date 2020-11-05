<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item>
        <el-button
          type="cyan"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd(1)"
          v-hasPermi="['system:ticket:add']"
          >添加典当</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd(2)"
          v-hasPermi="['system:ticket:add']"
          >添加续当</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:ticket:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:ticket:remove']"
          >删除</el-button
        >
      </el-col>
      <div class="top-right-btn">
        <el-tooltip class="item" effect="dark" content="刷新" placement="top">
          <el-button
            size="mini"
            circle
            icon="el-icon-refresh"
            @click="handleQuery"
          />
        </el-tooltip>
        <el-tooltip
          class="item"
          effect="dark"
          :content="showSearch ? '隐藏搜索' : '显示搜索'"
          placement="top"
        >
          <el-button
            size="mini"
            circle
            icon="el-icon-search"
            @click="showSearch = !showSearch"
          />
        </el-tooltip>
      </div>
    </el-row>

    <el-table
      v-loading="loading"
      :data="ticketList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="当票号" align="center" prop="pawnTicketCode" />
      <el-table-column label="机构名称" align="center" prop="orgName" />
      <el-table-column label="机构电话" align="center" prop="orgTel" />
      <el-table-column label="当户名称" align="center" prop="pawnerName" />
      <el-table-column label="联系电话" align="center" prop="pawnerTel" />
      <el-table-column label="身份证号" align="center" prop="pawnerIdCard" />
      <el-table-column
        label="证件类型（身份证）"
        align="center"
        prop="pawnerCert"
      />
      <el-table-column label="联系人" align="center" prop="contactor" />
      <el-table-column label="地址" align="center" prop="pawnerAddr" />
      <el-table-column label="商品名称" align="center" prop="goodsName" />
      <el-table-column
        label="票据类型"
        align="center"
        prop="type"
        :formatter="handleType"
      />
      <el-table-column
        label="合同状态"
        align="center"
        prop="status"
        :formatter="handleStatus"
      />

      <el-table-column
        label="查看合同"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            v-if="scope.row.status > 0"
            @click="showContractBtn(scope.row)"
            >查看合同</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- <div id="iframe-container" v-if="open1">
      <iframe
        :src="contractUrl"
        scrolling="auto"
        frameborder="0"
        id="iframe"
      ></iframe>
    </div> -->
    <el-dialog :visible.sync="open1" class="abow_dialog" append-to-body>
      <div style="height: 1000px">
        <iframe
          id="show-iframe"
          frameborder="0"
          scrolling="yes"
          style="background-color:transparent; position：absolute;width: 100%;
         height:100%; top: 0;left:0;bottom:0;"
          :src="contractUrl"
        ></iframe>
      </div>
    </el-dialog>

    <!-- 添加或修改【请填写功能名称】对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="150px">
        <el-form-item label="票据种类">
          <el-select v-model="form.type" :style="{ width: '100%' }">
            <el-option
              v-for="item in typeOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="机构">
          <el-select v-model="form.orgId" :style="{ width: '100%' }">
            <el-option
              v-for="item in orgOptions"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="除当票外双方其他约定">
          <el-select v-model="form.otherOrder" :style="{ width: '100%' }">
            <el-option
              v-for="item in otherOrderOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="典当开始时间" prop="pawnBeginTime">
          <el-date-picker
            clearable
            size="small"
            style="width: 300px"
            v-model="form.pawnBeginTime"
            type="date"
            value-format="yyyy-MM-dd"
          >
          </el-date-picker>
        </el-form-item>

        <el-form-item label="首期典当结束时间" prop="beginPawnEndTime">
          <el-date-picker
            clearable
            size="small"
            style="width: 300px"
            v-model="form.beginPawnEndTime"
            type="date"
            value-format="yyyy-MM-dd"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="当票号" prop="pawnTicketCode">
          <el-input v-model="form.pawnTicketCode" placeholder="请输入当票号" />
        </el-form-item>
        <el-form-item label="当户名称" prop="pawnerName">
          <el-input v-model="form.pawnerName" placeholder="请输入当户名称" />
        </el-form-item>
        <el-form-item label="联系电话" prop="pawnerTel">
          <el-input v-model="form.pawnerTel" placeholder="请输入联系电话" />
        </el-form-item>
        <el-form-item label="当户身份证号" prop="pawnerIdCard">
          <el-input
            v-model="form.pawnerIdCard"
            placeholder="请输入当户身份证号"
          />
        </el-form-item>

        <el-form-item label="联系人" prop="contactor">
          <el-input v-model="form.contactor" placeholder="请输入联系人" />
        </el-form-item>
        <el-form-item label="地址" prop="pawnerAddr">
          <el-input v-model="form.pawnerAddr" placeholder="请输入地址" />
        </el-form-item>
        <el-form-item label="商品名称" prop="goodsName">
          <el-input v-model="form.goodsName" placeholder="请输入商品名称" />
        </el-form-item>
        <el-form-item label="估价" prop="authPriceTest">
          <el-input v-model="form.authPriceTest" placeholder="请输入估价" />
        </el-form-item>
        <el-form-item label="鉴定价" prop="authPrice">
          <el-input v-model="form.authPrice" placeholder="请输入鉴定价" />
        </el-form-item>
        <el-form-item label="折当率" prop="equivalentRatio">
          <el-input v-model="form.equivalentRatio" placeholder="请输入折当率" />
        </el-form-item>
        <el-form-item label="合计" prop="loanMoney">
          <el-input v-model="form.loanMoney" placeholder="请输入合计" />
        </el-form-item>
        <el-form-item label="实付金额（小写）" prop="userMoney">
          <el-input v-model="form.userMoney" placeholder="请输入实付金额" />
        </el-form-item>
        <el-form-item label="综合费用（小写）" prop="pawnMoney">
          <el-input v-model="form.pawnMoney" placeholder="请输入综合费用" />
        </el-form-item>
        <el-form-item label="典当金额（大写）" prop="loanMoneyCN">
          <el-input v-model="form.loanMoneyCN" placeholder="请输入典当金额" />
        </el-form-item>
        <el-form-item label="实付金额（大写）" prop="userMoneyCN">
          <el-input v-model="form.userMoneyCN" placeholder="请输入实付金额" />
        </el-form-item>
        <el-form-item label="综合费用（大写）" prop="pawnMoneyCN">
          <el-input v-model="form.pawnMoneyCN" placeholder="请输入综合费用" />
        </el-form-item>
        <el-form-item label="月费率" prop="rate">
          <el-input v-model="form.rate" placeholder="请输入月费率" />
        </el-form-item>
        <el-form-item label="月利率" prop="moneyRate">
          <el-input v-model="form.moneyRate" placeholder="请输入月利率" />
        </el-form-item>

        <el-form-item label="复核" prop="checker">
          <el-input v-model="form.checker" placeholder="请输入复核" />
        </el-form-item>
        <el-form-item label="经办" prop="handler">
          <el-input v-model="form.handler" placeholder="请输入经办" />
        </el-form-item>
        <el-form-item label="备注" prop="userId">
          <el-input
            v-model="form.userId"
            type="textarea"
            placeholder="请输入备注"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog
      :title="title2"
      :visible.sync="open2"
      width="800px"
      append-to-body
    >
      <el-form ref="form2" :model="form2" :rules="rules" label-width="150px">
        <el-form-item label="票据种类">
          <el-select v-model="form2.type" :style="{ width: '100%' }">
            <el-option
              v-for="item in typeOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="机构">
          <el-select v-model="form2.orgId" :style="{ width: '100%' }">
            <el-option
              v-for="item in orgOptions"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="续当开始时间" prop="pawnBeginTime">
          <el-date-picker
            clearable
            size="small"
            style="width: 300px"
            v-model="form2.repawnBeginTime"
            type="date"
            value-format="yyyy-MM-dd"
          >
          </el-date-picker>
        </el-form-item>

        <el-form-item label="续当结束时间" prop="pawnBeginTime">
          <el-date-picker
            clearable
            size="small"
            style="width: 300px"
            v-model="form2.repawnEndTime"
            type="date"
            value-format="yyyy-MM-dd"
          >
          </el-date-picker>
        </el-form-item>

        <el-form-item label="当票号" prop="pawnTicketCode">
          <el-input v-model="form2.pawnTicketCode" placeholder="请输入当票号" />
        </el-form-item>
        <el-form-item label="当户名称" prop="pawnerName">
          <el-input v-model="form2.pawnerName" placeholder="请输入当户名称" />
        </el-form-item>
        <el-form-item label="联系电话" prop="pawnerTel">
          <el-input v-model="form2.pawnerTel" placeholder="请输入联系电话" />
        </el-form-item>
        <el-form-item label="当户身份证号" prop="pawnerIdCard">
          <el-input
            v-model="form2.pawnerIdCard"
            placeholder="请输入当户身份证号"
          />
        </el-form-item>

        <el-form-item label="联系人" prop="contactor">
          <el-input v-model="form2.contactor" placeholder="请输入联系人" />
        </el-form-item>
        <el-form-item label="地址" prop="pawnerAddr">
          <el-input v-model="form2.pawnerAddr" placeholder="请输入地址" />
        </el-form-item>
        <el-form-item label="商品名称" prop="goodsName">
          <el-input v-model="form2.goodsName" placeholder="请输入商品名称" />
        </el-form-item>
        <el-form-item label="原典当金额（大写）" prop="loanMoneyCN">
          <el-input
            v-model="form2.loanMoneyCN"
            placeholder="请输入原典当金额"
          />
        </el-form-item>
        <el-form-item label="原典当金额（小写）" prop="loanMoney">
          <el-input v-model="form2.loanMoney" placeholder="请输入原典当金额" />
        </el-form-item>

        <el-form-item label="续当综合费用（大写）" prop="costCN">
          <el-input v-model="form2.costCN" placeholder="请输入续当综合费用" />
        </el-form-item>
        <el-form-item label="续当综合费用（小写）" prop="cost">
          <el-input v-model="form2.cost" placeholder="请输入续当综合费用" />
        </el-form-item>

        <el-form-item label="当户应付上期利息（大写）" prop="moneyCostCN">
          <el-input
            v-model="form2.moneyCostCN"
            placeholder="请输入当户应付上期利息"
          />
        </el-form-item>
        <el-form-item label="当户应付上期利息（小写）" prop="moneyCost">
          <el-input
            v-model="form2.moneyCost"
            placeholder="请输入当户应付上期利息"
          />
        </el-form-item>

        <el-form-item label="当户总计交付金额（大写）" prop="repawnTotalCN">
          <el-input
            v-model="form2.repawnTotalCN"
            placeholder="请输入当户总计交付金额"
          />
        </el-form-item>
        <el-form-item label="当户总计交付金额（小写）" prop="repawnTotal">
          <el-input
            v-model="form2.repawnTotal"
            placeholder="请输入当户总计交付金额"
          />
        </el-form-item>

        <el-form-item label="月费率" prop="rate">
          <el-input v-model="form2.rate" placeholder="请输入月费率" />
        </el-form-item>
        <el-form-item label="月利率" prop="moneyRate">
          <el-input v-model="form2.moneyRate" placeholder="请输入月利率" />
        </el-form-item>

        <el-form-item label="复核" prop="checker">
          <el-input v-model="form2.checker" placeholder="请输入复核" />
        </el-form-item>
        <el-form-item label="经办" prop="handler">
          <el-input v-model="form2.handler" placeholder="请输入经办" />
        </el-form-item>
        <el-form-item label="备注" prop="userId">
          <el-input
            v-model="form2.userId"
            type="textarea"
            placeholder="请输入备注"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm2">确 定</el-button>
        <el-button @click="cancel2">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listTicket,
  getTicket,
  delTicket,
  addTicket,
  updateTicket,
  exportTicket,
  showContract,
  findOrg,
} from "@/api/ticket/pawnTicket";

export default {
  name: "Ticket",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 【请填写功能名称】表格数据
      ticketList: [],
      // 弹出层标题
      title: "",
      title2: "",
      // 是否显示弹出层
      open: false,
      open1: false,
      open2: false,
      contractUrl: null,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        pawnTicketCode: null,
        orgName: null,
        orgTel: null,
        orgAdress: null,
        businessLicense: null,
        pawnerName: null,
        pawnerTel: null,
        pawnerIdCard: null,
        pawnerCert: null,
        contactor: null,
        pawnerAddr: null,
        goodsName: null,
        authPriceTest: null,
        authPrice: null,
        equivalentRatio: null,
        loanMoney: null,
        userMoney: null,
        pawnMoney: null,
        loanMoneyCN: null,
        userMoneyCN: null,
        pawnMoneyCN: null,
        pawnBeginTime: null,
        pawnEndTime: null,
        beginPawnEndTime: null,
        rate: null,
        moneyRate: null,
        cost: null,
        moneyCost: null,
        repawnTotal: null,
        costCN: null,
        repawnTotalCN: null,
        moneyCostCN: null,
        repawnBeginTime: null,
        repawnEndTime: null,
        checker: null,
        handler: null,
        userId: null,
        otherOrder: null,
        modifyTime: null,
        type: null,
        contractId: null,
        status: null,
        signTime: null,
      },
      // 表单参数
      form: {},
      form2: {},
      // 表单校验
      rules: {},
      typeOptions: [],
      otherOrderOptions: [
        {
          label: "无",
          value: 1,
        },
        {
          label: "有",
          value: 2,
        },
      ],
      ddOptions: [
        {
          label: "典当",
          value: 1,
        },
      ],
      xdOptions: [
        {
          label: "续当",
          value: 2,
        },
      ],
      orgOptions: [],
    };
  },
  created() {
    this.getList();
    this.findOrgM();
  },
  methods: {
    /** 查询【请填写功能名称】列表 */
    getList() {
      this.loading = true;
      listTicket(this.queryParams).then((response) => {
        this.ticketList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    findOrgM() {
      findOrg().then((response) => {
        this.orgOptions = response;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    cancel2() {
      this.open2 = false;
      this.reset2();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        pawnTicketCode: null,
        orgName: null,
        orgTel: null,
        orgAdress: null,
        businessLicense: null,
        pawnerName: null,
        pawnerTel: null,
        pawnerIdCard: null,
        pawnerCert: null,
        contactor: null,
        pawnerAddr: null,
        goodsName: null,
        authPriceTest: null,
        authPrice: null,
        equivalentRatio: null,
        loanMoney: null,
        userMoney: null,
        pawnMoney: null,
        loanMoneyCN: null,
        userMoneyCN: null,
        pawnMoneyCN: null,
        pawnBeginTime: null,
        pawnEndTime: null,
        beginPawnEndTime: null,
        rate: null,
        moneyRate: null,
        cost: null,
        moneyCost: null,
        repawnTotal: null,
        costCN: null,
        repawnTotalCN: null,
        moneyCostCN: null,
        repawnBeginTime: null,
        repawnEndTime: null,
        checker: null,
        handler: null,
        userId: null,
        remark: null,
        otherOrder: null,
        createTime: null,
        modifyTime: null,
        type: null,
        contractId: null,
        status: "0",
        signTime: null,
      };
      this.resetForm("form");
    },
    reset2() {
      this.form2 = {
        id: null,
        pawnTicketCode: null,
        orgName: null,
        orgTel: null,
        orgAdress: null,
        businessLicense: null,
        pawnerName: null,
        pawnerTel: null,
        pawnerIdCard: null,
        pawnerCert: null,
        contactor: null,
        pawnerAddr: null,
        goodsName: null,
        authPriceTest: null,
        authPrice: null,
        equivalentRatio: null,
        loanMoney: null,
        userMoney: null,
        pawnMoney: null,
        loanMoneyCN: null,
        userMoneyCN: null,
        pawnMoneyCN: null,
        pawnBeginTime: null,
        pawnEndTime: null,
        beginPawnEndTime: null,
        rate: null,
        moneyRate: null,
        cost: null,
        moneyCost: null,
        repawnTotal: null,
        costCN: null,
        repawnTotalCN: null,
        moneyCostCN: null,
        repawnBeginTime: null,
        repawnEndTime: null,
        checker: null,
        handler: null,
        userId: null,
        remark: null,
        otherOrder: null,
        createTime: null,
        modifyTime: null,
        type: null,
        contractId: null,
        status: "0",
        signTime: null,
      };
      this.resetForm("form");
    },
    handleType(row, column) {
      if (row.type == 1) {
        return "典当";
      } else if (row.type == 2) {
        return "续当";
      }
    },
    handleStatus(row, column) {
      if (row.status == 0) {
        return "未生成合同";
      } else if (row.status == 1) {
        return "待签署";
      } else if (row.status == 2) {
        return "已签署";
      } else if (row.status == 3) {
        return "已退回";
      } else if (row.status == 4) {
        return "已作废";
      }
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd(type) {
      this.reset();
      console.info(type);
      if (type == 1) {
        this.open = true;
        this.title = "添加";
        this.typeOptions = this.ddOptions;
      } else if (type == 2) {
        this.typeOptions = this.xdOptions;
        this.open2 = true;
        this.title2 = "添加";
      }
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getTicket(id).then((response) => {
        if (row.type == 1) {
          this.typeOptions = this.ddOptions;
        } else if (row.type == 2) {
          this.typeOptions = this.xdOptions;
        }
        this.form = response.data;
        this.open = true;
        this.title = "修改【请填写功能名称】";
      });
    },
    showContractBtn(row) {
      showContract(row.id).then((response) => {
        // this.form = response.data;
        this.open1 = true;
        // // this.title = "修改【请填写功能名称】";
        this.contractUrl = response.pageUrl;
        // window.location.href = response.pageUrl;
        // console.info(response.pageUrl);
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateTicket(this.form).then((response) => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addTicket(this.form).then((response) => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              }
            });
          }
        }
      });
    },
    submitForm2() {
      this.$refs["form2"].validate((valid) => {
        if (valid) {
          if (this.form2.id != null) {
            updateTicket(this.form2).then((response) => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open2 = false;
                this.getList();
              }
            });
          } else {
            addTicket(this.form2).then((response) => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open2 = false;
                this.getList();
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm(
        '是否确认删除【请填写功能名称】编号为"' + ids + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return delTicket(ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
        .catch(function () {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm("是否确认导出所有【请填写功能名称】数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return exportTicket(queryParams);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () {});
    },
  },
};
</script>
<style>
.abow_dialog {
  display: flex;
  justify-content: center;
  align-items: Center;
  overflow: hidden;
  .el-dialog {
    margin: 0 auto !important;
    height: 90%;
    overflow: hidden;
    .el-dialog__body {
      position: absolute;
      left: 0;
      top: 54px;
      bottom: 0;
      right: 0;
      padding: 0;
      z-index: 1;
      overflow: hidden;
      overflow-y: auto;
    }
  }
}
</style>