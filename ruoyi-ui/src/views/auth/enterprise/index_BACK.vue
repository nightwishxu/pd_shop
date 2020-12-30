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
        label="店铺名称"
        prop="storeName"
      >
        <el-input
          v-model="queryParams.storeName"
          placeholder="请输入店铺名称"
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
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:enterprise:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:enterprise:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:enterprise:remove']"
        >删除</el-button>
      </el-col>
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
      :data="enterpriseList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column
        type="selection"
        width="55"
        align="center"
      />
      <el-table-column
        label="id"
        align="center"
        prop="id"
      />
      <el-table-column
        label="店铺名称"
        align="center"
        prop="storeName"
      />
      <el-table-column
        label="店铺logo"
        align="center"
        prop="logo"
      />
      <el-table-column
        label="店铺介绍"
        align="center"
        prop="storeIntroduce"
      />
      <el-table-column
        label="企业名称"
        align="center"
        prop="enterpriseName"
      />
      <el-table-column
        label="法人姓名"
        align="center"
        prop="legalPersonName"
      />
      <el-table-column
        label="法人身份证"
        align="center"
        prop="legalPersonCard"
      />
      <el-table-column
        label="营业执照副本照片"
        align="center"
        prop="businessLicensePhoto"
      />
      <el-table-column
        label="法人身份证正面照片"
        align="center"
        prop="legalPersonCardFront"
      />
      <el-table-column
        label="法人身份证反面照片"
        align="center"
        prop="legalPersonCardBack"
      />
      <el-table-column
        label="创建人"
        align="center"
        prop="createUser"
      />
      <el-table-column
        label="0审核中，1审核成功 2审核失败 3无效"
        align="center"
        prop="state"
      />
      <el-table-column
        label="不通过原因"
        align="center"
        prop="refuseInfo"
      />
      <el-table-column
        label="机构表id"
        align="center"
        prop="orgId"
      />
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
            v-hasPermi="['system:enterprise:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:enterprise:remove']"
          >删除</el-button>
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

    <!-- 添加或修改【请填写功能名称】对话框 -->
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
          label="店铺名称"
          prop="storeName"
        >
          <el-input
            v-model="form.storeName"
            placeholder="请输入店铺名称"
          />
        </el-form-item>
        <el-form-item
          label="店铺logo"
          prop="logo"
        >
          <el-input
            v-model="form.logo"
            placeholder="请输入店铺logo"
          />
        </el-form-item>
        <el-form-item
          label="店铺介绍"
          prop="storeIntroduce"
        >
          <el-input
            v-model="form.storeIntroduce"
            type="textarea"
            placeholder="请输入内容"
          />
        </el-form-item>
        <el-form-item
          label="企业名称"
          prop="enterpriseName"
        >
          <el-input
            v-model="form.enterpriseName"
            placeholder="请输入企业名称"
          />
        </el-form-item>
        <el-form-item
          label="法人姓名"
          prop="legalPersonName"
        >
          <el-input
            v-model="form.legalPersonName"
            placeholder="请输入法人姓名"
          />
        </el-form-item>
        <el-form-item
          label="法人身份证"
          prop="legalPersonCard"
        >
          <el-input
            v-model="form.legalPersonCard"
            placeholder="请输入法人身份证"
          />
        </el-form-item>
        <el-form-item
          label="营业执照副本照片"
          prop="businessLicensePhoto"
        >
          <el-input
            v-model="form.businessLicensePhoto"
            placeholder="请输入营业执照副本照片"
          />
        </el-form-item>
        <el-form-item
          label="法人身份证正面照片"
          prop="legalPersonCardFront"
        >
          <el-input
            v-model="form.legalPersonCardFront"
            placeholder="请输入法人身份证正面照片"
          />
        </el-form-item>
        <el-form-item
          label="法人身份证反面照片"
          prop="legalPersonCardBack"
        >
          <el-input
            v-model="form.legalPersonCardBack"
            placeholder="请输入法人身份证反面照片"
          />
        </el-form-item>
        <el-form-item
          label="创建人"
          prop="createUser"
        >
          <el-input
            v-model="form.createUser"
            placeholder="请输入创建人"
          />
        </el-form-item>
        <el-form-item
          label="0审核中，1审核成功 2审核失败 3无效"
          prop="state"
        >
          <el-input
            v-model="form.state"
            placeholder="请输入0审核中，1审核成功 2审核失败 3无效"
          />
        </el-form-item>
        <el-form-item
          label="不通过原因"
          prop="refuseInfo"
        >
          <el-input
            v-model="form.refuseInfo"
            placeholder="请输入不通过原因"
          />
        </el-form-item>
        <el-form-item
          label="机构表id"
          prop="orgId"
        >
          <el-input
            v-model="form.orgId"
            placeholder="请输入机构表id"
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
import { listEnterprise, getEnterprise, delEnterprise, addEnterprise, updateEnterprise, exportEnterprise } from "@/api/auth/enterprise";

export default {
  name: "Enterprise",
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
      enterpriseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        storeName: null,
        logo: null,
        storeIntroduce: null,
        enterpriseName: null,
        legalPersonName: null,
        legalPersonCard: null,
        businessLicensePhoto: null,
        legalPersonCardFront: null,
        legalPersonCardBack: null,
        createUser: null,
        state: null,
        refuseInfo: null,
        orgId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        state: [
          { required: true, message: "0审核中，1审核成功 2审核失败 3无效不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询【请填写功能名称】列表 */
    getList() {
      this.loading = true;
      listEnterprise(this.queryParams).then(response => {
        this.enterpriseList = response.rows;
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
        storeName: null,
        logo: null,
        storeIntroduce: null,
        enterpriseName: null,
        legalPersonName: null,
        legalPersonCard: null,
        businessLicensePhoto: null,
        legalPersonCardFront: null,
        legalPersonCardBack: null,
        createUser: null,
        createTime: null,
        state: null,
        refuseInfo: null,
        orgId: null
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
      this.title = "添加【请填写功能名称】";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getEnterprise(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改【请填写功能名称】";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateEnterprise(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addEnterprise(this.form).then(response => {
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
      this.$confirm('是否确认删除【请填写功能名称】编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delEnterprise(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有【请填写功能名称】数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportEnterprise(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
