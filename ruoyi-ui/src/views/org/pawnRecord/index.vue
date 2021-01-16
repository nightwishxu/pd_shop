<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item
        label="当品名称"
        prop="goodsName"
      >
        <el-input
          v-model="queryParams.goodsName"
          placeholder="请输入当品名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item
        label="机构名称"
        prop="orgId"
      >
        <el-input
          v-model="queryParams.orgId"
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
        >搜索</el-button>
        <el-button
          icon="el-icon-refresh"
          size="mini"
          @click="resetQuery"
        >重置</el-button>
      </el-form-item>
    </el-form>

    <el-row
      :gutter="10"
      class="mb8"
    >
      <div class="top-right-btn">
        <el-tooltip
          class="item"
          effect="dark"
          content="刷新"
          placement="top"
        >
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
      :data="pawnList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column
        type="selection"
        width="55"
        align="center"
      />
      <el-table-column
        label="藏品名称"
        align="center"
        prop="goodsName1"
      />
      <el-table-column
        label="鉴定结果"
        align="center"
        prop="authResult"
        :formatter="handleAuthResult"
      />
      <el-table-column
        label="鉴定价格"
        align="center"
        prop="authPrice"
      />

      <el-table-column
        label="宝贝图片"
        align="center"
        prop="images"
      >
        <template scope="scope">
          <span
            v-for="(item, index) in scope.row.images.split(',')"
            :key="index"
          >
            <el-popover
              placement="left"
              trigger="click"
              width="300"
            >
              <el-image :src="item" />
              <el-image
                slot="reference"
                :src="item"
                :alt="item"
                style="max-height: 50px; max-width: 50px; padding: 5px"
              />
            </el-popover>
          </span>
        </template>
      </el-table-column>

      <el-table-column
        label="典当状态"
        align="center"
        prop="state"
        :formatter="handleState"
      />

      <el-table-column
        label="典当记录"
        align="center"
        prop="pawnRecordList"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            @click="handleForm1(scope.row)"
          >查看典当记录</el-button>
        </template>
      </el-table-column>

      <!-- <el-table-column
        label="电子凭证"
        align="center"
        prop="pawnTicket"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            @click="handleForm3(scope.row)"
          >查看电子凭证</el-button>
        </template>
      </el-table-column> -->

      <el-table-column
        label="典当开始时间"
        align="center"
        prop="pawnBeginTime"
        width="180"
      >
        <template slot-scope="scope">
          <span v-if="
              scope.row.sate != 1 &&
              scope.row.sate != -1 &&
              scope.row.pawnBeginTime
            ">{{ scope.row.pawnBeginTime | dateYMDFormat }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="当前典当到期时间"
        align="center"
        prop="pawnEndTime"
        width="180"
      >
        <template slot-scope="scope">
          <span v-if="
              scope.row.sate != 1 &&
              scope.row.sate != -1 &&
              scope.row.pawnEndTime
            ">{{ scope.row.pawnEndTime | dateYMDFormat }}</span>
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

    <el-dialog
      :title="title1"
      :visible.sync="open1"
      width="1000px"
      append-to-body
    >
      <el-table
        :data="pawnLogList"
        @selection-change="handleSelectionChange"
        :row-class-name="rowClassName"
      >
        <el-table-column
          prop="id"
          width="55"
          align="center"
          label="次数"
        />
        <el-table-column
          label="藏品名称"
          align="center"
          prop="goodsName"
        />
        <el-table-column
          label="机构名称"
          align="center"
          prop="orgName"
        />
        <el-table-column
          label="金额"
          align="center"
          prop="money"
        />
        <el-table-column
          label="典当时长"
          align="center"
          prop="pawnMonth"
          :formatter="handlePawnMonth"
        />
        <el-table-column
          label="用户姓名"
          align="center"
          prop="userName"
        />
        <el-table-column
          label="类型"
          align="center"
          prop="type"
          :formatter="handleType"
        />
        <el-table-column
          label="银行"
          align="center"
          prop="tradeCardBank"
        />
        <el-table-column
          label="交易所用的银行卡"
          align="center"
          prop="tradeCardCode"
        />
        <el-table-column
          label="电子凭证"
          align="center"
          prop="contractUrl"
        >

          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              v-if="scope.row.contractUrl!=undefined && scope.row.contractUrl!=null && scope.row.contractUrl!=''"
              @click="viewTicket(scope.row)"
            >查看电子凭证</el-button>
          </template>

        </el-table-column>
      </el-table>
    </el-dialog>

    <!-- 添加或修改典当对话框 -->
    <!-- <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户id" />
        </el-form-item>
        <el-form-item label="当品id" prop="goodsId">
          <el-input v-model="form.goodsId" placeholder="请输入当品id" />
        </el-form-item>
        <el-form-item label="贷款额度" prop="loansPrice">
          <el-input v-model="form.loansPrice" placeholder="请输入贷款额度" />
        </el-form-item>
        <el-form-item label="期望利率" prop="loansRate">
          <el-input v-model="form.loansRate" placeholder="请输入期望利率" />
        </el-form-item>
        <el-form-item label="是否确认(0:未确认  1:确认) " prop="isVerify">
          <el-input
            v-model="form.isVerify"
            placeholder="请输入是否确认(0:未确认  1:确认) "
          />
        </el-form-item>
        <el-form-item label="确认的机构id" prop="orgId">
          <el-input v-model="form.orgId" placeholder="请输入确认的机构id" />
        </el-form-item>
        <el-form-item label="出价人" prop="orgUserId">
          <el-input v-model="form.orgUserId" placeholder="请输入出价人" />
        </el-form-item>
        <el-form-item label="用户选择中标机构时间" prop="orgSelectedTime">
          <el-date-picker
            clearable
            size="small"
            style="width: 200px"
            v-model="form.orgSelectedTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择用户选择中标机构时间"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="收款人" prop="payeeName">
          <el-input v-model="form.payeeName" placeholder="请输入收款人" />
        </el-form-item>
        <el-form-item label="收款人电话" prop="payeePhone">
          <el-input v-model="form.payeePhone" placeholder="请输入收款人电话" />
        </el-form-item>
        <el-form-item label="收款人银行" prop="payeeBankName">
          <el-input
            v-model="form.payeeBankName"
            placeholder="请输入收款人银行"
          />
        </el-form-item>
        <el-form-item label="收款人银行卡号" prop="payeeBankCardCode">
          <el-input
            v-model="form.payeeBankCardCode"
            placeholder="请输入收款人银行卡号"
          />
        </el-form-item>
        <el-form-item label="机构打款凭证" prop="payeeTicket">
          <el-input
            v-model="form.payeeTicket"
            placeholder="请输入机构打款凭证"
          />
        </el-form-item>
        <el-form-item label="0无1已到账" prop="payeeState">
          <el-input v-model="form.payeeState" placeholder="请输入0无1已到账" />
        </el-form-item>
        <el-form-item label="打款人" prop="payName">
          <el-input v-model="form.payName" placeholder="请输入打款人" />
        </el-form-item>
        <el-form-item label="打款人电话" prop="payPhone">
          <el-input v-model="form.payPhone" placeholder="请输入打款人电话" />
        </el-form-item>
        <el-form-item label="打款人银行名称" prop="payBankName">
          <el-input
            v-model="form.payBankName"
            placeholder="请输入打款人银行名称"
          />
        </el-form-item>
        <el-form-item label="打款人银行卡号" prop="payBacnkCardCode">
          <el-input
            v-model="form.payBacnkCardCode"
            placeholder="请输入打款人银行卡号"
          />
        </el-form-item>
        <el-form-item label="当号" prop="pawnTicketCode">
          <el-input v-model="form.pawnTicketCode" placeholder="请输入当号" />
        </el-form-item>
        <el-form-item label="当票" prop="pawnTicket">
          <el-input v-model="form.pawnTicket" placeholder="请输入当票" />
        </el-form-item>
        <el-form-item
          label="-1已取消1竞拍中  2已典当 3赎当 4绝当 5宝祥兜底"
          prop="state"
        >
          <el-input
            v-model="form.state"
            placeholder="请输入-1已取消1竞拍中  2已典当 3赎当 4绝当 5宝祥兜底"
          />
        </el-form-item>
        <el-form-item
          label="0.无1.用户主动放弃本次竞拍 2.用户自主选择中标机构 3.跟随系统 4.机构拒绝"
          prop="userState"
        >
          <el-input
            v-model="form.userState"
            placeholder="请输入0.无1.用户主动放弃本次竞拍 2.用户自主选择中标机构 3.跟随系统 4.机构拒绝"
          />
        </el-form-item>
        <el-form-item label="典当开始" prop="pawnBeginTime">
          <el-date-picker
            clearable
            size="small"
            style="width: 200px"
            v-model="form.pawnBeginTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择典当开始"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="典当结束" prop="pawnEndTime">
          <el-date-picker
            clearable
            size="small"
            style="width: 200px"
            v-model="form.pawnEndTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择典当结束"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="初始当金" prop="beginMoney">
          <el-input v-model="form.beginMoney" placeholder="请输入初始当金" />
        </el-form-item>
        <el-form-item label="初始当期" prop="beginPawnMonth">
          <el-input
            v-model="form.beginPawnMonth"
            placeholder="请输入初始当期"
          />
        </el-form-item>
        <el-form-item label="初始典当结束时间" prop="beginPawnEndTime">
          <el-date-picker
            clearable
            size="small"
            style="width: 200px"
            v-model="form.beginPawnEndTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择初始典当结束时间"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="当金" prop="money">
          <el-input v-model="form.money" placeholder="请输入当金" />
        </el-form-item>
        <el-form-item label="当期(半月)" prop="pawnTime">
          <el-input v-model="form.pawnTime" placeholder="请输入当期(半月)" />
        </el-form-item>
        <el-form-item label="最新一期当金" prop="lastMoney">
          <el-input v-model="form.lastMoney" placeholder="请输入最新一期当金" />
        </el-form-item>
        <el-form-item label="最新一期当期" prop="lastPawnMonth">
          <el-input
            v-model="form.lastPawnMonth"
            placeholder="请输入最新一期当期"
          />
        </el-form-item>
        <el-form-item label="最新一期时间" prop="lastPawnTime">
          <el-date-picker
            clearable
            size="small"
            style="width: 200px"
            v-model="form.lastPawnTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择最新一期时间"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="综合利率(%) 最终中标的综合利率" prop="rate">
          <el-input
            v-model="form.rate"
            placeholder="请输入综合利率(%) 最终中标的综合利率"
          />
        </el-form-item>
        <el-form-item label="利息利率(%) 最终中标的利率" prop="moneyRate">
          <el-input
            v-model="form.moneyRate"
            placeholder="请输入利息利率(%) 最终中标的利率"
          />
        </el-form-item>
        <el-form-item label="机构逾期利率" prop="overdueRate">
          <el-input
            v-model="form.overdueRate"
            placeholder="请输入机构逾期利率"
          />
        </el-form-item>
        <el-form-item label="首期综合费" prop="pawnMoney">
          <el-input v-model="form.pawnMoney" placeholder="请输入首期综合费" />
        </el-form-item>
        <el-form-item label="用户实际得款" prop="userMoney">
          <el-input v-model="form.userMoney" placeholder="请输入用户实际得款" />
        </el-form-item>
        <el-form-item label="机构服务费率(%)" prop="platformRate">
          <el-input
            v-model="form.platformRate"
            placeholder="请输入机构服务费率(%)"
          />
        </el-form-item>
        <el-form-item label="平台服务费" prop="platformMoney">
          <el-input
            v-model="form.platformMoney"
            placeholder="请输入平台服务费"
          />
        </el-form-item>
        <el-form-item label="0未缴纳1已缴纳平台服务费" prop="platformState">
          <el-input
            v-model="form.platformState"
            placeholder="请输入0未缴纳1已缴纳平台服务费"
          />
        </el-form-item>
        <el-form-item
          label="0-默认值1申请赎当2用户已打款3机构确认收款"
          prop="redeemState"
        >
          <el-input
            v-model="form.redeemState"
            placeholder="请输入0-默认值1申请赎当2用户已打款3机构确认收款"
          />
        </el-form-item>
        <el-form-item label="用户赎当打款凭证" prop="redeemTicket">
          <el-input
            v-model="form.redeemTicket"
            placeholder="请输入用户赎当打款凭证"
          />
        </el-form-item>
        <el-form-item label="用户赎当时间" prop="redeemTime">
          <el-date-picker
            clearable
            size="small"
            style="width: 200px"
            v-model="form.redeemTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择用户赎当时间"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="赎当滞纳金" prop="redeemOverdue">
          <el-input
            v-model="form.redeemOverdue"
            placeholder="请输入赎当滞纳金"
          />
        </el-form-item>
        <el-form-item label="赎当应付利息" prop="redeemInterest">
          <el-input
            v-model="form.redeemInterest"
            placeholder="请输入赎当应付利息"
          />
        </el-form-item>
        <el-form-item label="赎当当票" prop="redeemPawnTicket">
          <el-input
            v-model="form.redeemPawnTicket"
            placeholder="请输入赎当当票"
          />
        </el-form-item>
        <el-form-item label="赎当当票" prop="modifyTime">
          <el-date-picker
            clearable
            size="small"
            style="width: 200px"
            v-model="form.modifyTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择赎当当票"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="典当行名称" prop="orgName">
          <el-input v-model="form.orgName" placeholder="请输入典当行名称" />
        </el-form-item>
        <el-form-item label="典当行电话" prop="orgTel">
          <el-input v-model="form.orgTel" placeholder="请输入典当行电话" />
        </el-form-item>
        <el-form-item label="典当行地址" prop="orgAddress">
          <el-input v-model="form.orgAddress" placeholder="请输入典当行地址" />
        </el-form-item>
        <el-form-item label="典当行许可证编号" prop="orgLicense">
          <el-input
            v-model="form.orgLicense"
            placeholder="请输入典当行许可证编号"
          />
        </el-form-item>
        <el-form-item label="机构印章" prop="orgSeal">
          <el-input v-model="form.orgSeal" placeholder="请输入机构印章" />
        </el-form-item>
        <el-form-item label="用户名称" prop="userName">
          <el-input v-model="form.userName" placeholder="请输入用户名称" />
        </el-form-item>
        <el-form-item label="用户电话" prop="userPhone">
          <el-input v-model="form.userPhone" placeholder="请输入用户电话" />
        </el-form-item>
        <el-form-item label="用户地址" prop="userAddress">
          <el-input v-model="form.userAddress" placeholder="请输入用户地址" />
        </el-form-item>
        <el-form-item label="用户身份证" prop="userIdCard">
          <el-input v-model="form.userIdCard" placeholder="请输入用户身份证" />
        </el-form-item>
        <el-form-item label="当品名称" prop="goodsName">
          <el-input v-model="form.goodsName" placeholder="请输入当品名称" />
        </el-form-item>
        <el-form-item label="最新续当id" prop="lastPawnContinueId">
          <el-input
            v-model="form.lastPawnContinueId"
            placeholder="请输入最新续当id"
          />
        </el-form-item>
        <el-form-item label="合同id" prop="contractId">
          <el-input v-model="form.contractId" placeholder="请输入合同id" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog> -->
  </div>
</template>

<script>
import {
  listPawn,
  getPawn,
  delPawn,
  addPawn,
  updatePawn,
  exportPawn,
  pawnRecord,
  pawnLogSearch,
} from "@/api/org/pawn";
import { pawnMsg } from "@/api/org/org";
export default {
  name: "Pawn",
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
      // 典当表格数据
      pawnList: [],
      pawnLogList: [],
      // 弹出层标题
      title: "",
      title1: "",
      // 是否显示弹出层
      open: false,
      open1: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        goodsId: null,
        loansPrice: null,
        loansRate: null,
        isVerify: null,
        orgId: null,
        orgUserId: null,
        orgSelectedTime: null,
        payeeName: null,
        payeePhone: null,
        payeeBankName: null,
        payeeBankCardCode: null,
        payeeTicket: null,
        payeeState: null,
        payName: null,
        payPhone: null,
        payBankName: null,
        payBacnkCardCode: null,
        pawnTicketCode: null,
        pawnTicket: null,
        state: null,
        userState: null,
        pawnBeginTime: null,
        pawnEndTime: null,
        beginMoney: null,
        beginPawnMonth: null,
        beginPawnEndTime: null,
        money: null,
        pawnTime: null,
        lastMoney: null,
        lastPawnMonth: null,
        lastPawnTime: null,
        rate: null,
        moneyRate: null,
        overdueRate: null,
        pawnMoney: null,
        userMoney: null,
        platformRate: null,
        platformMoney: null,
        platformState: null,
        redeemState: null,
        redeemTicket: null,
        redeemTime: null,
        redeemOverdue: null,
        redeemInterest: null,
        redeemPawnTicket: null,
        modifyTime: null,
        orgName: null,
        orgTel: null,
        orgAddress: null,
        orgLicense: null,
        orgSeal: null,
        userName: null,
        userPhone: null,
        userAddress: null,
        userIdCard: null,
        goodsName: null,
        lastPawnContinueId: null,
        contractId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userId: [
          { required: true, message: "用户id不能为空", trigger: "blur" },
        ],
        goodsId: [
          { required: true, message: "当品id不能为空", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询典当列表 */
    getList() {
      this.loading = true;
      pawnRecord(this.queryParams).then((response) => {
        this.pawnList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    rowClassName({ row, rowIndex }) {
      //把每一行的索引放进row.id
      row.id = rowIndex + 1;
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
        userId: null,
        goodsId: null,
        loansPrice: null,
        loansRate: null,
        isVerify: null,
        orgId: null,
        orgUserId: null,
        orgSelectedTime: null,
        payeeName: null,
        payeePhone: null,
        payeeBankName: null,
        payeeBankCardCode: null,
        payeeTicket: null,
        payeeState: null,
        payName: null,
        payPhone: null,
        payBankName: null,
        payBacnkCardCode: null,
        pawnTicketCode: null,
        pawnTicket: null,
        state: null,
        userState: null,
        pawnBeginTime: null,
        pawnEndTime: null,
        beginMoney: null,
        beginPawnMonth: null,
        beginPawnEndTime: null,
        money: null,
        pawnTime: null,
        lastMoney: null,
        lastPawnMonth: null,
        lastPawnTime: null,
        rate: null,
        moneyRate: null,
        overdueRate: null,
        pawnMoney: null,
        userMoney: null,
        platformRate: null,
        platformMoney: null,
        platformState: null,
        redeemState: null,
        redeemTicket: null,
        redeemTime: null,
        redeemOverdue: null,
        redeemInterest: null,
        redeemPawnTicket: null,
        createTime: null,
        modifyTime: null,
        orgName: null,
        orgTel: null,
        orgAddress: null,
        orgLicense: null,
        orgSeal: null,
        userName: null,
        userPhone: null,
        userAddress: null,
        userIdCard: null,
        goodsName: null,
        lastPawnContinueId: null,
        contractId: null,
      };
      this.resetForm("form");
    },
    handlePawnMonth(row, column){
      if(row.pawnMonth!=undefined && row.pawnMonth!=null ){
        return row.pawnMonth * 15;
      }
    },
    viewTicket(row, column){
         window.open(row.contractUrl,"_blank")
    },
    handleAuthResult(row, column) {
      if (row.authResult === 0) {
        return "未鉴定";
      } else if (row.authResult === 1) {
        return "鉴定中";
      } else if (row.authResult === 2) {
        return "无法鉴定";
      } else if (row.authResult === 3) {
        return "赝品";
      } else if (row.authResult === 4) {
        return "真品'";
      } else {
        return "";
      }
    },
    handleState(row, column) {
      if (row.state === -1) {
        return "典当已取消";
      } else if (row.state === 1) {
        return "竞拍中";
      } else if (row.state === 2) {
        return "已典当";
      } else if (row.state === 3) {
        return "赎当";
      } else if (row.state === 4) {
        return "绝当'";
      } else if (row.state === 5) {
        return "宝祥兜底";
      } else {
        return "";
      }
    },
    handleType(row, column) {
      if (row.type === 0) {
        return "鉴定真品";
      } else if (row.type === 1) {
        return "典当";
      } else if (row.type === 2) {
        return "续当";
      } else if (row.type === 3) {
        return "赎当";
      } else if (row.type === 4) {
        return "绝当'";
      } else if (row.type === 5) {
        return "交易";
      } else if (row.type === 6) {
        return "卖给平台";
      } else {
        return "";
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
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加典当";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getPawn(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改典当";
      });
    },
    handleForm1(row) {
      pawnLogSearch({ id: row.goodsId }).then((response) => {
        this.pawnLogList = response.data;
        this.open1 = true;
        this.title1 = "记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updatePawn(this.form).then((response) => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addPawn(this.form).then((response) => {
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
      this.$confirm('是否确认删除典当编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return delPawn(ids);
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
      this.$confirm("是否确认导出所有典当数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return exportPawn(queryParams);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () {});
    },
  },
};
</script>
