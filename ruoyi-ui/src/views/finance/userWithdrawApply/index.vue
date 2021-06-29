<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >

      <el-form-item label="申请时间">
        <el-date-picker
          type="datetimerange"
          v-model="createTimeRange"
          format="yyyy.MM.dd HH:mm:ss"
          value-format="yyyy.MM.dd HH:mm:ss"
          :style="{width: '100%'}"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          range-separator="至"
          clearable
        ></el-date-picker>
      </el-form-item>

      <el-form-item
        label="状态"
        prop="status"
      >
        <el-select
          v-model="queryParams.state"
          placeholder="请选择状态"
          clearable
          size="small"
        >
          <el-option
            v-for="(item, index) in statusOptions"
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

    <el-row
      :gutter="10"
      class="mb8"
    >
      <!-- <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:apply:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:apply:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:apply:remove']"
        >删除</el-button>
      </el-col> -->
      <!-- <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:apply:export']"
        >导出</el-button>
      </el-col> -->
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
            @click="showSearch=!showSearch"
          />
        </el-tooltip>
      </div>
    </el-row>

    <el-table
      v-loading="loading"
      :data="applyList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column
        type="selection"
        width="55"
        align="center"
      />
      <el-table-column
        label="用户名"
        align="center"
        prop="userName"
      />

      <el-table-column
        label="提现账户"
        align="center"
        prop="account"
      />

      <el-table-column
        label="金额"
        align="center"
        prop="amount"
      />

      <el-table-column
        label="账户余额"
        align="center"
        prop="balance"
      />

      <el-table-column
        label="收款账户"
        align="center"
        prop="tradeType"
      >
        <template scope="scope">
          <div v-if="scope.row.tradeType === 1">支付宝</div>
          <div v-if="scope.row.tradeType === 2">微信</div>
          <div v-if="scope.row.tradeType === 10">
            <div v-if="scope.row.bankCardNo">
              <p>{{ scope.row.bankCardNo }}</p>
              <p>{{ scope.row.bankCardName }}</p>
            </div>
            <div v-else>--</div>
          </div>
          <div v-else>--</div>
        </template>
      </el-table-column>
      <el-table-column
        label="状态"
        align="center"
        prop="status"
        :formatter="handleStatus"
      />
      <el-table-column
        label="申请时间"
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
          <div v-if="scope.row.status===0">
            <el-button
              size="mini"
              type="text"
              @click="chargeStatusBtn(scope.row.id,1)"
              v-hasPermi="['system:apply:edit']"
            >审核成功</el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改机构提现申请对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="open"
      width="500px"
      append-to-body
    >
      <el-form
        ref="form"
        :model="form"
        :rules="rules"
        label-width="80px"
      >
        <el-form-item
          label="机构编号"
          prop="orgId"
        >
          <el-input
            v-model="form.orgId"
            placeholder="请输入机构编号"
          />
        </el-form-item>
        <el-form-item
          label="用户编号"
          prop="userId"
        >
          <el-input
            v-model="form.userId"
            placeholder="请输入用户编号"
          />
        </el-form-item>
        <el-form-item
          label="金额"
          prop="amount"
        >
          <el-input
            v-model="form.amount"
            placeholder="请输入金额"
          />
        </el-form-item>
        <el-form-item label="状态 0 待审核 1审核成功 2审核失败">
          <el-radio-group v-model="form.status">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="0余额1支付宝2微信10线下银行卡">
          <el-select
            v-model="form.tradeType"
            placeholder="请选择0余额1支付宝2微信10线下银行卡"
          >
            <el-option
              label="请选择字典生成"
              value=""
            />
          </el-select>
        </el-form-item>
        <el-form-item
          label="0余额1支付宝2微信10线下银行卡"
          prop="createAccount"
        >
          <el-input
            v-model="form.createAccount"
            placeholder="请输入0余额1支付宝2微信10线下银行卡"
          />
        </el-form-item>
        <el-form-item
          label="0余额1支付宝2微信10线下银行卡"
          prop="modifyTime"
        >
          <el-date-picker
            clearable
            size="small"
            style="width: 200px"
            v-model="form.modifyTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择0余额1支付宝2微信10线下银行卡"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item
          label="0余额1支付宝2微信10线下银行卡"
          prop="modifyAccount"
        >
          <el-input
            v-model="form.modifyAccount"
            placeholder="请输入0余额1支付宝2微信10线下银行卡"
          />
        </el-form-item>
      </el-form>
      <div
        slot="footer"
        class="dialog-footer"
      >
        <el-button
          type="primary"
          @click="submitForm"
        >确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listApply, getApply, delApply, addApply, updateApply, exportApply,changeState } from "@/api/finance/userApply";

export default {
  name: "Apply",
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
      // 机构提现申请表格数据
      applyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      createTimeRange: null,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orgId: null,
        userId: null,
        amount: null,
        status: null,
        tradeType: null,
        createAccount: null,
        modifyTime: null,
        modifyAccount: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
       statusOptions: [
        {
          label: "审核中",
          value: 0,
        },
        {
          label: "审核成功",
          value: 1,
        }
      ],
    };
  },
  created() {
    this.getList();
  },
  methods: {
     handleStatus(row, column) {
      /* 0 待审核 1审核成功 2审核失败 */
      if (row.status === 0) {
        return "审核中";
      } else if (row.status === 1) {
        return "审核成功";
      } else if (row.status === 2) {
         return "审核失败";
      } else {
        return "--";
      }
      return row.hasBack == 1 ? "是" : "否";
    },
    handleTradeType(row, column) {
      if (row.tradeType === 1) {
        return "支付宝";
      } else if (row.tradeType === 2) {
        return "微信";
      } else if (row.tradeType === 10) {
        return row.bankCardNo ? row.bankCardNo + "</br>" + row.bankCardName : "--";
      } else {
        return "--";
      }
    },
    chargeStatusBtn(id,status){
       changeState({'id':id,'status':status}).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.getList();
              }
            });
    },
    /** 查询机构提现申请列表 */
    getList() {
      this.loading = true;
      if(this.createTimeRange!=undefined && this.createTimeRange!=null){
        this.queryParams.startTime = this.createTimeRange[0]
      this.queryParams.endTime = this.createTimeRange[1]
      }
      listApply(this.queryParams).then(response => {
        this.applyList = response.rows;
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
        orgId: null,
        userId: null,
        amount: null,
        status: 0,
        tradeType: null,
        createTime: null,
        createAccount: null,
        modifyTime: null,
        modifyAccount: null
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加机构提现申请";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getApply(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改机构提现申请";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateApply(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addApply(this.form).then(response => {
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
      this.$confirm('是否确认删除机构提现申请编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delApply(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有机构提现申请数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportApply(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
