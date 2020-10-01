<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <!-- <el-form-item label="展示位置" prop="location">
        <el-input
          v-model="queryParams.location"
          placeholder="请输入展示位置"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="图片" prop="img">
        <el-input
          v-model="queryParams.img"
          placeholder="请输入图片"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="文字简介" prop="discription">
        <el-input
          v-model="queryParams.discription"
          placeholder="请输入文字简介"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择类型" clearable size="small">
          <el-option label="请选择字典生成" value />
        </el-select>
      </el-form-item>
      <el-form-item label="内容" prop="content">
        <el-input
          v-model="queryParams.content"
          placeholder="请输入内容"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="排序(倒序)" prop="sortOrder">
        <el-input
          v-model="queryParams.sortOrder"
          placeholder="请输入排序(倒序)"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>-->
      <el-form-item>
        <el-button
          type="cyan"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <!-- <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button> -->
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['admin:ad:add']"
          >新增</el-button
        >
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:ad:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:ad:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['admin:ad:export']"
        >导出</el-button>
      </el-col>-->
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
      :data="adList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="广告" align="center" prop="id" /> -->
      <el-table-column label="关键词" align="center" prop="content">
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime">
        <template slot-scope="scope">{{
          scope.row.createTime | dateYMDHMSFormat
        }}</template>
      </el-table-column>
      <el-table-column label="排序" align="center" prop="sortOrder" />
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
            v-hasPermi="['admin:ad:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['admin:ad:remove']"
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

    <!-- 添加或修改认证商城广告对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="780px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="设置搜索关键词" prop="content">
          <el-input v-model="form.content" placeholder="请输入搜索关键词" />
        </el-form-item>

        <!-- cateCodeSon -->
        <el-form-item label="排序" prop="sortOrder">
          <el-input v-model="form.sortOrder" placeholder="请输入排序" />
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
  listAd,
  getAd,
  delAd,
  addAd,
  goodsList,
  videoList,
  dpList,
  updateAd,
  exportAd,
} from "@/api/admin/ad";
import SingleUpload from "@/components/Upload/singleUpload";
import Editor from "@/components/Editor";

export default {
  name: "Ad",
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
      // 认证商城广告表格数据
      adList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        location: null,
        img: null,
        discription: null,
        type: null,
        content: null,
        sortOrder: null,
      },
      // 表单参数
      form: {},
      form1: {},
      form2: {},
      // 表单校验
      rules: {
        content: [
          { required: true, message: "关键词不能为空", trigger: "blur" },
        ],
        sortOrder: [
          { required: true, message: "排序(倒序)不能为空", trigger: "blur" },
        ],
      },
      adminGoodsOptions: [],
      deadPawnGoodsOptions: [],
      storeOptions: [],
      sexOptions: [],
      content1: false,
      content2: false,
      content3: false,
      content4: false,
      content5: false,
      typeOptions: [
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
        {
          label: "认证商城商品",
          value: 3,
        },
        {
          label: "绝当商城商品",
          value: 4,
        },
        {
          label: "店铺详情",
          value: 5,
        },
      ],
    };
  },
  created() {
    this.getDicts("sys_user_sex").then((response) => {
      this.sexOptions = response.data;
    });
    goodsList(null, 1).then((response) => {
      this.adminGoodsOptions = response.data;
    });
    goodsList(null, 2).then((response) => {
      this.deadPawnGoodsOptions = response.data;
    });

    dpList(null).then((response) => {
      this.storeOptions = response.data;
    });
    this.getList();
  },
  methods: {
    /** 查询认证商城广告列表 */
    getList() {
      this.loading = true;
      this.queryParams.location = 5;
      listAd(this.queryParams).then((response) => {
        this.adList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    handleType(row, column) {
      if (row.type === 1) {
        return "网址";
      } else if (row.type === 2) {
        return "富文本";
      } else if (row.type === 3) {
        return "认证商城商品";
      } else if (row.type === 4) {
        return "绝当商城商品";
      } else if (row.type === 5) {
        return "店铺详情";
      } else {
        return "--";
      }
    },
    selectType(value) {
      if (value === 1) {
        this.content1 = true;
        this.content2 = false;
        this.content3 = false;
        this.content4 = false;
        this.content5 = false;
      } else if (value === 2) {
        this.content1 = false;
        this.content2 = true;
        this.content3 = false;
        this.content4 = false;
        this.content5 = false;
      } else if (value === 3) {
        this.content1 = false;
        this.content2 = false;
        this.content3 = true;
        this.content4 = false;
        this.content5 = false;
      } else if (value === 4) {
        this.content1 = false;
        this.content2 = false;
        this.content3 = false;
        this.content4 = true;
        this.content5 = false;
      } else if (value === 5) {
        this.content1 = false;
        this.content2 = false;
        this.content3 = false;
        this.content4 = false;
        this.content5 = true;
      } else {
        this.content1 = false;
        this.content2 = false;
        this.content3 = false;
        this.content4 = false;
        this.content5 = false;
      }
      this.form.content = null;
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
        location: null,
        img: null,
        discription: null,
        type: null,
        content: null,
        sortOrder: null,
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
      this.title = "添加认关键词";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getAd(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改关键词";
      });
    },

    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          this.form.location = 5;
          this.form.type = "0";
          this.form.img = "";
          console.info("form:" + JSON.stringify(this.form));
          if (this.form.id != null) {
            updateAd(this.form).then((response) => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addAd(this.form).then((response) => {
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
        '是否确认删除认证商城广告编号为"' + ids + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return delAd(ids);
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
      this.$confirm("是否确认导出所有认证商城广告数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return exportAd(queryParams);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () {});
    },
  },
};
</script>
