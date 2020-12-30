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
        label="订单号"
        prop="code"
      >
        <el-input
          v-model="queryParams.orderCode"
          placeholder="请输入订单号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item
        label="商品名称"
        prop="goodsName"
      >
        <el-input
          v-model="queryParams.goodsName"
          placeholder="请输入商品名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item
        label="支付方式"
        prop="payType"
      >
        <el-select
          v-model="queryParams.payType"
          placeholder="请选择支付方式"
          clearable
          size="small"
        >
          <el-option
            v-for="(item, index) in payTypeOptions"
            :key="index"
            :label="item.label"
            :value="item.value"
            :disabled="item.disabled"
          ></el-option>
        </el-select>
      </el-form-item>

      <el-form-item
        label="订单状态"
        prop="state"
      >
        <el-select
          v-model="queryParams.state"
          placeholder="请选择状态"
          clearable
          size="small"
        >
          <el-option
            v-for="(item, index) in stateOptions"
            :key="index"
            :label="item.label"
            :value="item.value"
            :disabled="item.disabled"
          ></el-option>
        </el-select>
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

    <!-- 
   <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['admin:goods:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:goods:edit']"
        >修改</el-button>
      </el-col>
   <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:goods:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['admin:goods:export']"
        >导出</el-button>
      </el-col>
    </el-row>-->
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
      :data="orderList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column
        type="selection"
        width="55"
        align="center"
      />
      <el-table-column
        label="订单号"
        align="center"
        prop="code"
      />
      <el-table-column
        label="商品名称"
        align="center"
        prop="goodsName"
      />
      <el-table-column
        label="宝贝所属机构端名称"
        align="center"
        prop="orgName"
      />
      <el-table-column
        label="订单状态"
        align="center"
        prop="state"
        :formatter="handleState"
      />
      <el-table-column
        label="支付方式"
        align="center"
        prop="payType"
        :formatter="handlePayType"
      />
      <el-table-column
        label="收件人姓名"
        align="center"
        prop="shipUser"
      />
      <el-table-column
        label="收件人地址"
        align="center"
        prop="shipAddress"
        width="200"
        show-overflow-tooltip
      />
      <el-table-column
        label="收件人电话"
        align="center"
        prop="shipPhone"
      />
      <!-- <el-table-column label="流水单号" align="center" prop="payLogId" width="200" /> -->

      <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
        width="180"
      >
        <template slot-scope="scope">{{
          scope.row.createTime | dateYMDHMSFormat
        }}</template>
      </el-table-column>

      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['admin:order:edit']"
          >查看</el-button>
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
      width="300px"
      append-to-body
    >
      <el-form
        ref="form1"
        :model="form"
        :rules="rules"
        label-width="150px"
      >
        <el-form-item
          label="快递编号"
          prop="shipCode"
        >
          <el-input
            v-model="form.shipCode"
            placeholder="请输入快递编号"
          />
        </el-form-item>
      </el-form>
      <div
        slot="footer"
        class="dialog-footer"
      >
        <el-button
          type="primary"
          @click="updateState"
        >确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog
      :title="title2"
      :visible.sync="open2"
      width="300px"
      append-to-body
    >
      <el-form
        ref="form2"
        :model="form"
        :rules="rules"
        label-width="15px"
      >
        <el-form-item
          label="拒绝原因"
          prop="refundNotVerifyReason"
        >
          <el-input
            v-model="form.shipCode"
            placeholder="请输入拒绝原因"
          />
        </el-form-item>
      </el-form>
      <div
        slot="footer"
        class="dialog-footer"
      >
        <el-button
          type="primary"
          @click="refusing"
        >确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog
      :title="title3"
      :visible.sync="open3"
      width="300px"
      append-to-body
    >
      <el-form
        ref="form2"
        :model="form"
        :rules="rules"
        label-width="15px"
      >
        <el-form-item
          label="退款回寄地址"
          prop="refundNotVerifyReason"
        >
          <el-input
            v-model="form.backAddress"
            placeholder="请输入退款回寄地址"
          />
        </el-form-item>
        <el-form-item
          label="退款回寄收件人"
          prop="refundNotVerifyReason"
        >
          <el-input
            v-model="form.backUser"
            placeholder="请输入退款回寄收件人"
          />
        </el-form-item>
        <el-form-item
          label="退款回寄联系电话"
          prop="refundNotVerifyReason"
        >
          <el-input
            v-model="form.backPhone"
            placeholder="请输入退款回寄联系电话"
          />
        </el-form-item>
      </el-form>
      <div
        slot="footer"
        class="dialog-footer"
      >
        <el-button
          type="primary"
          @click="sendRefDatar"
        >确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改订单对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="open"
      width="800px"
      append-to-body
    >
      <div style="height: 50vh; overflaow: auto">
        <el-form
          ref="form"
          :model="form"
          :rules="rules"
          label-width="100px"
        >
          <el-col span="12">
            <el-form-item
              label="订单号"
              prop="code"
            >
              <el-input
                v-model="form.code"
                placeholder="请输入订单号"
              />
            </el-form-item>
          </el-col>
          <el-col span="12">
            <el-form-item
              label="收件人姓名"
              prop="shipUser"
            >
              <el-input
                v-model="form.shipUser"
                placeholder="请输入收件人姓名"
              />
            </el-form-item>
          </el-col>
          <el-col span="12">
            <el-form-item
              label="快递公司"
              prop="shipFirm"
            >
              <el-input
                v-model="form.shipFirm"
                placeholder="请输入快递公司"
              />
            </el-form-item>
          </el-col>
          <el-col span="12">
            <el-form-item
              label="快递编号"
              prop="shipCode"
            >
              <el-input
                v-model="form.shipCode"
                placeholder="请输入快递编号"
              />
            </el-form-item>
          </el-col>
          <el-form-item
            label="收件人地址"
            prop="shipAddress"
          >
            <el-input
              v-model="form.shipAddress"
              placeholder="请输入收件人地址"
            />
          </el-form-item>
          <el-form-item
            label="收件人电话"
            prop="shipPhone"
          >
            <el-input
              v-model="form.shipPhone"
              placeholder="请输入收件人电话"
            />
          </el-form-item>
          <el-form-item
            label="商品名称"
            prop="goodsName"
          >
            <el-input
              v-model="form.goodsName"
              placeholder="请输入商品名称"
            />
          </el-form-item>
          <el-col span="12">
            <el-form-item
              label="商品价格"
              prop="goodsPrice"
            >
              <el-input
                v-model="form.goodsPrice"
                placeholder="请输入商品价格"
              />
            </el-form-item>
          </el-col>
          <el-col span="12">
            <el-form-item
              label="订单金额"
              prop="price"
            >
              <el-input
                v-model="form.price"
                placeholder="请输入订单金额"
              />
            </el-form-item>
          </el-col>
          <el-col span="12">
            <el-form-item
              label="订单状态"
              prop="state"
            >
              <el-input v-model="form.state" />
            </el-form-item>
          </el-col>
          <el-col span="12">
            <el-form-item
              label="结算状态"
              prop="isBalance"
            >
              <el-input v-model="form.isBalance" />
            </el-form-item>
          </el-col>
        </el-form>
      </div>
      <!-- <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>-->
    </el-dialog>
  </div>
</template>

<script>
import {
  listOrder,
  getOrder,
  delOrder,
  addOrder,
  updateState,
  sendRefData,
  updateOrder,
  exportOrder,
} from "@/api/admin/order";

export default {
  name: "Order",
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
      // 订单表格数据
      orderList: [],
      // 弹出层标题
      title: "",
      title1: "",
      title2: "",
      title3: "",
      // 是否显示弹出层
      open: false,
      open1: false,
      open2: false,
      open3: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        code: null,
        userId: null,
        goodsId: null,
        goodsName: null,
        goodsImg: null,
        goodsSource: null,
        orgId: null,
        goodsPrice: null,
        goodsCost: null,
        price: null,
        video: null,
        state: null,
        refState: null,
        payLogId: null,
        isBalance: null,
        modifyTime: null,
        balanceTime: null,
        payTime: null,
        shipUser: null,
        shipFirm: null,
        shipCode: null,
        shipAddress: null,
        shipPhone: null,
        couponId: null,
        couponValue: null,
        couponInfo: null,
        isDel: null,
        refundReason: null,
        refundNotVerifyReason: null,
        backCode: null,
        backAddress: null,
        backUser: null,
        backPhone: null,
        backState: null,
        payType: null,
        commentState: null,
      },
      // 表单参数
      form: {},
      form1: {},
      form2: {},
      form3: {},
      // 表单校验
      rules: {
        code: [{ required: true, message: "订单号不能为空", trigger: "blur" }],
        userId: [{ required: true, message: "用户不能为空", trigger: "blur" }],
        goodsSource: [
          {
            required: true,
            message: "1平台2机构3服务商4供应商5寄拍不能为空",
            trigger: "blur",
          },
        ],
        orgId: [{ required: true, message: "0平台不能为空", trigger: "blur" }],
        state: [
          {
            required: true,
            message:
              "订单状态-1已取消1待付款2已付款3已发货4确认收货5已评价不能为空",
            trigger: "blur",
          },
        ],
        isBalance: [
          {
            required: true,
            message: "1已结算0未结算不能为空",
            trigger: "blur",
          },
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" },
        ],
      },
      payTypeOptions: [
        {
          label: "微信",
          value: 1,
        },
        {
          label: "支付宝",
          value: 2,
        },
        {
          label: "线下",
          value: 3,
        },
      ],
      //1已取消1待付款2已付款3已发货4确认收货5已评价不能为空
      stateOptions: [
        {
          label: "已取消",
          value: -1,
        },
        {
          label: "待付款",
          value: 1,
        },
        {
          label: "已付款",
          value: 2,
        },
        {
          label: "已发货",
          value: 3,
        },
        {
          label: "确认收货",
          value: 4,
        },
        {
          label: "已评价",
          value: 5,
        },
      ],
    };
  },
  created() {
    this.getList();
  },
  methods: {
    handleState(row, column) {
      console.log(row);
      /* 1已取消1待付款2已付款3已发货4确认收货5已评价 */
      if (row.state === -1) {
        return "已取消";
      } else if (row.state === 1) {
        return "待付款";
      } else if (row.state === 2) {
        return "已付款";
      } else if (row.state === 3) {
        return "已发货";
      } else if (row.state === 4) {
        return "确认收货";
      } else if (row.state === 5) {
        return "已评价";
      } else {
        return "--";
      }
      return row.hasBack == 1 ? "是" : "否";
    },

    //支付方式 1微信 2支付宝 3线下
    handlePayType(row, column) {
      if (row.payType === 1) {
        return "微信";
      } else if (row.payType === 2) {
        return "支付宝";
      } else if (row.payType === 3) {
        return "线下";
      } else {
        return "--";
      }
    },
    /** 查询订单列表 */
    getList() {
      this.loading = true;
      // this.queryParams = 1;
      this.queryParams.goodsSource = 3;
      listOrder(this.queryParams).then((response) => {
        this.orderList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.open1 = false;
      this.open2 = false;
      this.open3 = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        code: null,
        userId: null,
        goodsId: null,
        goodsName: null,
        goodsImg: null,
        goodsSource: null,
        orgId: null,
        goodsPrice: null,
        goodsCost: null,
        price: null,
        video: null,
        state: null,
        refState: null,
        payLogId: null,
        isBalance: null,
        createTime: null,
        modifyTime: null,
        balanceTime: null,
        payTime: null,
        shipUser: null,
        shipFirm: null,
        shipCode: null,
        shipAddress: null,
        shipPhone: null,
        couponId: null,
        couponValue: null,
        couponInfo: null,
        isDel: null,
        refundReason: null,
        refundNotVerifyReason: null,
        backCode: null,
        backAddress: null,
        backUser: null,
        backPhone: null,
        backState: null,
        payType: null,
        commentState: null,
      };
      this.resetForm("form");
    },
    reset1() {
      this.form1 = {
        id: null,
        shipCode: null,
      };
      this.resetForm("form1");
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
      this.title = "添加订单";
    },
    //发货
    delivery(row) {
      this.reset();
      const id = row.id;
      console.info(id);

      this.form1.id = id;

      this.open1 = true;
      this.title1 = "发货";
    },
    //拒绝退货
    refusing(row) {
      this.reset();
      const id = row.id;
      console.info(id);

      this.form2.id = id;

      this.open2 = true;
      this.title2 = "拒绝退货";
    },
    sendBackData(row) {
      this.reset();
      const id = row.id;
      console.info(id);

      this.form3.id = id;

      this.open3 = true;
      this.title3 = "同意退款";
    },
    //同意退款
    agreeRef(row) {
      var data = { id: row.id, refState: 3 };
      updateOrder(data).then((response) => {
        if (response.code === 200) {
          this.getList();
        }
      });
    },
    //确认退款
    refSuccess(row) {
      var data = { id: row.id, refState: 4 };
      updateOrder(data).then((response) => {
        if (response.code === 200) {
          this.getList();
        }
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getOrder(id).then((response) => {
        this.form = response.data;
        var state = this.form.state;
        var isBalance = this.form.isBalance;
        var stateStr;
        if (state === -1) {
          stateStr = "已取消";
        } else if (state === 1) {
          stateStr = "待付款";
        } else if (state === 2) {
          stateStr = "已付款";
        } else if (state === 3) {
          stateStr = "已发货";
        } else if (state === 4) {
          stateStr = "确认收货";
        } else if (state === 5) {
          stateStr = "已评价";
        } else {
          stateStr = "--";
        }
        var isBalanceStr = "";
        switch (isBalance) {
          case 1:
            isBalanceStr = "已结算";
            break;
          case 0:
            isBalanceStr = "未结算";
            break;
        }
        this.form.state = stateStr;
        this.form.isBalance = isBalanceStr;
        this.open = true;
        this.title = "修改订单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateOrder(this.form).then((response) => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addOrder(this.form).then((response) => {
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
    //发货
    updateState() {
      console.info(this.form1);
      this.$refs["form1"].validate((response) => {
        if (valid) {
          updateState(this.form1).then(() => {
            if (response.code === 200) {
              this.msgSuccess("修改成功");
              this.open1 = false;
              this.getList();
            }
          });
        }
      });
    },
    refusing() {
      this.$refs["form2"].validate((valid) => {
        if (valid) {
          sendRefData(this.form2).then(() => {
            if (response.code === 200) {
              this.msgSuccess("修改成功");
              this.open2 = false;
              this.getList();
            }
          });
        }
      });
    },
    sendRefDatar() {
      this.$refs["form3"].validate((valid) => {
        if (valid) {
          sendRefData(this.form3).then(() => {
            if (response.code === 200) {
              this.msgSuccess("修改成功");
              this.open3 = false;
              this.getList();
            }
          });
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除订单编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return delOrder(ids);
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
      this.$confirm("是否确认导出所有订单数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return exportOrder(queryParams);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () {});
    },
  },
};
</script>
