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
          v-hasPermi="['system:focus:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:focus:edit']"
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
          v-hasPermi="['system:focus:remove']"
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
      :data="focusList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="图片" align="center" prop="img">
        <template scope="scope">
          <el-popover placement="right" title trigger="click">
            <el-image
              slot="reference"
              :src="scope.row.img"
              :alt="scope.row.img"
              style="max-height: 50px; max-width: 50px"
            ></el-image>
            <el-image :src="scope.row.img"></el-image>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        label="跳转类型 "
        align="center"
        prop="redirectType"
        :formatter="handleRedirectType"
      />
      <el-table-column label="跳转内容" align="center" prop="redirectContent" />
      <el-table-column label="排序(倒序)" align="center" prop="sortOrder" />
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
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:focus:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:focus:remove']"
            >删除</el-button
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

    <!-- 添加或修改焦点图对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="封面" prop="img">
          <single-upload
            v-model="form.img"
            style="width: 300px; display: inline-block; margin-left: 10px"
          ></single-upload>
        </el-form-item>
        <el-form-item label="排序" prop="sortOrder">
          <el-input v-model="form.sortOrder" placeholder="请输入排序" />
        </el-form-item>
        <el-form-item label="跳转类型" prop="redirectType">
          <el-select
            v-model="form.redirectType"
            placeholder="请选择下拉选择"
            clearable
            :style="{ width: '100%' }"
            @change="selectRedirectType"
          >
            <el-option
              v-for="item in redirectTypeOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
              :disabled="item.disabled"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="网址" prop="redirectContent" v-if="content1">
          <el-input v-model="form.redirectContent" placeholder="请输入网址" />
        </el-form-item>
        <el-form-item label="详情" prop="content" v-if="content2">
          <Editor v-model="form.content" />
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
  listFocus,
  getFocus,
  delFocus,
  addFocus,
  updateFocus,
  exportFocus,
} from "@/api/operation/focus";
import SingleUpload from "@/components/Upload/singleUpload";
import Editor from "@/components/Editor";

export default {
  name: "Focus",
  components: { SingleUpload, Editor },
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
      // 焦点图表格数据
      focusList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        areaCode: null,
        img: null,
        location: null,
        restraint: null,
        redirectType: null,
        redirectContent: null,
        content: null,
        sortOrder: null,
      },
      // 表单参数
      form: {},
      content1: false,
      content2: false,
      // 表单校验
      rules: {
        img: [{ required: true, message: "图不能为空", trigger: "blur" }],
        location: [
          {
            required: true,
            message:
              "1:首页2:认证商城3:绝当品商城4:首页轮播5:首页头条字段6详情不能为空",
            trigger: "blur",
          },
        ],
        redirectType: [
          {
            required: true,
            message: "0:不跳转 1:网页 2:视频 3:商城: 不能为空",
            trigger: "blur",
          },
        ],
        sortOrder: [
          { required: true, message: "排序(倒序)不能为空", trigger: "blur" },
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" },
        ],
      },
      redirectTypeOptions: [
        {
          label: "不跳转",
          value: 0,
        },
        {
          label: "网址",
          value: 1,
        },
        {
          label: "富文本",
          value: 2,
        },
      ],
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询焦点图列表 */
    getList() {
      this.loading = true;
      this.queryParams.location = 1;
      listFocus(this.queryParams).then((response) => {
        this.focusList = response.rows;
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
        areaCode: null,
        img: null,
        location: null,
        restraint: null,
        redirectType: null,
        redirectContent: null,
        content: null,
        sortOrder: null,
        createTime: null,
      };
      this.resetForm("form");
    },
    handleRedirectType(row, cloumn) {
      if (row.redirectType == 0) {
        return "不跳转";
      } else if (row.redirectType == 1) {
        return "网址";
      } else if (row.redirectType == 2) {
        return "富文本";
      }
    },
    selectRedirectType(value) {
      if (value === 0) {
        this.content1 = false;
        this.content2 = false;
      } else if (value === 1) {
        this.content1 = true;
        this.content2 = false;
      } else if (value === 2) {
        this.content1 = false;
        this.content2 = true;
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
      this.title = "添加焦点图";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getFocus(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改焦点图";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.form.location = 1;
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateFocus(this.form).then((response) => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addFocus(this.form).then((response) => {
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
      this.$confirm('是否确认删除焦点图编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return delFocus(ids);
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
      this.$confirm("是否确认导出所有焦点图数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return exportFocus(queryParams);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () {});
    },
  },
};
</script>
