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
          v-hasPermi="['system:notify:add']"
          >创建草稿</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:notify:edit']"
          >修改未发送信息</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:notify:remove']"
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
      :data="notifyList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="标题" align="center" prop="title" />
      <el-table-column label="对象" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.userId == null || scope.row.userId == ''"
            >全部</span
          >
          <span v-else>{{ scope.row.account }}({{ scope.row.nickName }})</span>
        </template>
      </el-table-column>
      <el-table-column label="内容" align="center" prop="content" />
      <el-table-column
        label="状态"
        align="center"
        prop="state"
        :formatter="handleState"
      />
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
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改消息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="内容" prop="content">
          <el-input
            v-model="form.content"
            type="textarea"
            placeholder="请输入内容"
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
  listNotify,
  getNotify,
  delNotify,
  addNotify,
  updateNotify,
  exportNotify,
} from "@/api/operation/notify";

export default {
  name: "Notify",
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
      // 消息表格数据
      notifyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        title: null,
        content: null,
        redirectType: null,
        redirectContent: null,
        state: null,
        num: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [{ required: true, message: "标题不能为空", trigger: "blur" }],
        content: [{ required: true, message: "内容不能为空", trigger: "blur" }],
        redirectType: [
          {
            required: true,
            message: "0:不跳转 1:url不能为空",
            trigger: "blur",
          },
        ],
        state: [
          {
            required: true,
            message: "1:未发送 2:已发送不能为空",
            trigger: "blur",
          },
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    handleState(row, column) {
      if (row.state == 1) {
        return "未发送";
      } else if (row.state == 2) {
        return "已发送";
      }
    },

    /** 查询消息列表 */
    getList() {
      this.loading = true;
      listNotify(this.queryParams).then((response) => {
        this.notifyList = response.rows;
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
        userId: null,
        title: null,
        content: null,
        redirectType: null,
        redirectContent: null,
        state: null,
        num: null,
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
      this.title = "添加消息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getNotify(id).then((response) => {
        if (response.data.state == 2) {
          this.$alert("已发送的记录无法修改", "提示", {
            confirmButtonText: "确定",
            callback: (action) => {
              this.$message({
                type: "info",
                message: `action: ${action}`,
              });
            },
          });
          return;
        }
        this.form = response.data;
        this.open = true;
        this.title = "修改消息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateNotify(this.form).then((response) => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addNotify(this.form).then((response) => {
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
      this.$confirm('是否确认删除消息编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return delNotify(ids);
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
      this.$confirm("是否确认导出所有消息数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return exportNotify(queryParams);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () {});
    },
  },
};
</script>
