<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="专业领域" prop="domainId">
        <el-select v-model="queryParams.domainId" filterable>
          <el-option
            v-for="item in options"
            :key="item.id"
            :label="item.domainName"
            :value="item.id"
          >
          </el-option>
        </el-select>
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
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:experter:add']"
          >新增</el-button
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
      :data="experterList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="专家姓名" align="center" prop="expertName" />
      <el-table-column label="专家领域" align="center" prop="domainName" />
      <el-table-column label="联系电话" align="center" prop="phone" />
      <el-table-column label="创建时间" align="center" prop="createTime">
        <template slot-scope="scope">{{
          scope.row.createTime | dateYMDHMSFormat
        }}</template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改专家对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="专家姓名" prop="expertName">
          <el-input v-model="form.expertName" placeholder="请输入专家姓名" />
        </el-form-item>
        <el-form-item label="专业领域" prop="domainId">
          <el-select v-model="form.domainId">
            <el-option
              v-for="item in options"
              :key="item.id"
              :label="item.domainName"
              :value="item.id"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="联系电话" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入联系电话" />
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
  listExperter,
  getExperter,
  delExperter,
  addExperter,
  updateExperter,
  exportExperter,
} from "@/api/authApply/experter";
import { listDomain } from "@/api/authApply/domain";
export default {
  name: "Experter",
  data() {
    return {
      // 遮罩层
      loading: true,
      loading1: false,
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
      // 专家表格数据
      experterList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        expertName: null,
        domainId: null,
        phone: null,
      },
      // 表单参数
      form: {},
      options: {},
      // 表单校验
      rules: {
        expertName: [
          { required: true, message: "专家姓名不能为空", trigger: "blur" },
        ],
        domainId: [
          {
            required: true,
            message: "专家的专业领域id不能为空",
            trigger: "blur",
          },
        ],
      },
    };
  },
  created() {
    this.getList();
    this.remoteMethod(null);
  },
  methods: {
    remoteMethod(query) {
      listDomain().then((response) => {
        this.options = response;
      });
    },
    /** 查询专家列表 */
    getList() {
      this.loading = true;
      listExperter(this.queryParams).then((response) => {
        this.experterList = response.rows;
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
        expertName: null,
        domainId: null,
        phone: null,
        createTime: null,
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
      this.title = "添加专家";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getExperter(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改专家";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateExperter(this.form).then((response) => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addExperter(this.form).then((response) => {
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
      this.$confirm('是否确认删除专家编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return delExperter(ids);
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
      this.$confirm("是否确认导出所有专家数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return exportExperter(queryParams);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () {});
    },
  },
};
</script>
