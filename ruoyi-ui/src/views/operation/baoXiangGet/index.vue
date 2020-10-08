<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="当票号" prop="pawnTicketCode">
        <el-input
          v-model="queryParams.pawnTicketCode"
          placeholder="请输入当票号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item>
        <el-button
          type="cyan"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
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
      <el-table-column label="商品名称" align="center" prop="goodsName1" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-if="
              scope.row.pawnTicketCode == null ||
              scope.row.pawnTicketCode == undifined ||
              scope.row.pawnTicketCode == ''
            "
            >填写宝祥兜底当号</el-button
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

    <!-- 添加或修改【请填写功能名称】对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="宝祥兜底当号" prop="pawnTicketCode">
          <el-input
            v-model="form.pawnTicketCode"
            placeholder="请输入宝祥兜底当号"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
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
  baoxiangGet,
} from "@/api/operation/ticket";

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
      // 是否显示弹出层
      open: false,
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
      // 表单校验
      rules: {},
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询【请填写功能名称】列表 */
    getList() {
      this.loading = true;
      baoxiangGet(this.queryParams).then((response) => {
        this.ticketList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
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
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      this.form.id = row.id;
      this.open = true;
      this.title = "修改";
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
