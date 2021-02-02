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
        label="展示位置"
        prop="location"
      >
        <el-select
          v-model="queryParams.location"
          placeholder="请输入展示位置"
          clearable
          size="small"
        >
          <el-option
            v-for="(item, index) in locationOptions"
            :key="index"
            :label="item.label"
            :value="item.value"
            :disabled="item.disabled"
          ></el-option>
        </el-select>
      </el-form-item>

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
        >搜索</el-button>
        <!-- <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button> -->
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
          v-hasPermi="['admin:ad:add']"
        >新增</el-button>
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
      :data="adList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column
        type="selection"
        width="55"
        align="center"
      />
      <!-- <el-table-column label="广告" align="center" prop="id" /> -->
      <el-table-column
        label="文字简介"
        align="center"
        prop="discription"
      />
      <el-table-column
        <el-table-column
        label="图片"
        align="center"
        prop="img"
      >
        <template scope="scope">
          <img
            :src="scope.row.img"
            width="40"
            height="40"
          />
        </template>
      </el-table-column>
      <el-table-column
        label="位置"
        align="center"
        prop="location"
        :formatter="handleLocation"
      />
      <el-table-column
        label="类型"
        align="center"
        prop="type"
        :formatter="handleType"
      />
      <el-table-column
        label="排序(倒序)"
        align="center"
        prop="sortOrder"
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
            v-hasPermi="['admin:ad:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['admin:ad:remove']"
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

    <!-- 添加或修改认证商城广告对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="open"
      width="780px"
      append-to-body
    >
      <el-form
        ref="form"
        :model="form"
        :rules="rules"
        label-width="120px"
      >
        <!-- <el-form-item label="展示位置" prop="location">
          <el-input v-model="form.location" placeholder="请输入展示位置" />
        </el-form-item>-->
        <el-form-item
          label="图片"
          prop="img"
        >
          <single-upload
            v-model="form.img"
            style="width: 300px;display: inline-block;margin-left: 10px"
          ></single-upload>
        </el-form-item>
        <el-form-item
          label="文字简介"
          prop="discription"
        >
          <el-input
            v-model="form.discription"
            placeholder="请输入文字简介"
          />
        </el-form-item>
        <el-form-item label="位置">
          <el-select
            v-model="form.location"
            placeholder="请选择下拉选择"
            clearable
            :style="{width: '100%'}"
          >
            <el-option
              v-for="(item, index) in locationOptions"
              :key="index"
              :label="item.label"
              :value="item.value"
              :disabled="item.disabled"
            ></el-option>
          </el-select>
          <!-- cateCodeSon -->
        </el-form-item>

        <el-form-item label="类型">
          <el-select
            v-model="form.type"
            placeholder="请选择下拉选择"
            clearable
            :style="{width: '100%'}"
            @change="selectType"
          >
            <el-option
              v-for="(item, index) in typeOptions"
              :key="index"
              :label="item.label"
              :value="item.value"
              :disabled="item.disabled"
            ></el-option>
          </el-select>
          <!-- cateCodeSon -->
        </el-form-item>
        <el-form-item
          label="排序"
          prop="sortOrder"
        >
          <el-input
            v-model="form.sortOrder"
            placeholder="请输入排序"
          />
        </el-form-item>
        <el-form-item
          label="网址"
          prop="content"
          v-if="content1"
        >
          <el-input
            v-model="form.content"
            placeholder="请输入内容"
          />
        </el-form-item>
        <el-form-item
          label="详情"
          prop="content"
          v-if="content2"
        >
          <Editor v-model="form.content" />
        </el-form-item>
        <el-form-item
          label="认证商城商品"
          v-if="content3"
        >
          <el-select
            v-model="form.content"
            placeholder="请选择"
            :style="{width: '100%'}"
          >
            <el-option
              v-for="item in adminGoodsOptions"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <!-- <el-form-item label="用户性别">
          <el-select v-model="form.content" placeholder="请选择" v-if="content3">
            <el-option
              v-for="dict in sexOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>-->
        <el-form-item
          label="绝当商城商品"
          prop="content"
          v-if="content4"
        >
          <el-select
            v-model="form.content"
            placeholder="请选择"
            clearable
            :style="{width: '100%'}"
          >
            <el-option
              v-for="item in deadPawnGoodsOptions"
              :key="item.id"
              :label="item.name"
              :value="item.id"
              :disabled="item.disabled"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item
          label="店铺详情"
          v-if="content6"
        >
          <el-select
            v-model="form.content"
            placeholder="请选择"
            clearable
            :style="{width: '100%'}"
          >
            <el-option
              v-for="item in storeOptions"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <!-- <el-form-item label="网址" prop="content" id="content6">
          <el-input v-model="form.content" placeholder="请输入内容" />
        </el-form-item>-->
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
        img: [{ required: true, message: "图片不能为空", trigger: "blur" }],
        sortOrder: [
          { required: true, message: "排序(倒序)不能为空", trigger: "blur" },
        ],
        location: [
          { required: true, message: "位置不能为空", trigger: "blur" },
        ],
        type: [
          { required: true, message: "跳转类型不能为空", trigger: "blur" },
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
      content6: false,
      content7: true,
      locationOptions:[
        {
          label: "首页轮播图",
          value: 1,
        },
        {
          label: "认证商城轮播图",
          value: 2,
        },
        {
          label: "绝当商城轮播图",
          value: 3,
        },
        {
          label: "拍档头条",
          value: 4,
        },
        {
          label: "关键词搜索",
          value: 5,
        },
        {
          label: "首页",
          value: 6,
        },
        {
          label: "c端个人中心",
          value: 7,
        },
        {
          label: "商家入驻引导页",
          value: 8,
        },
        {
          label: "机构端个人中心",
          value: 9,
        },
        {
          label: "商家端个人中心",
          value: 10,
        },
        {
          label: "首页底部",
          value: 11,
        },
        {
          label: "优惠券",
          value: 12,
        },
      ],
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
          label: "视频",
          value: 5,
        },
        {
          label: "店铺详情",
          value: 6,
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
      } else if (row.type === 6) {
        return "店铺详情";
      } else {
        return "--";
      }
    },
    handleLocation(row, column) {
      if (row.location === 1) {
        return "首页轮播图";
      } else if (row.location === 2) {
        return "认证商城轮播图";
      } else if (row.location === 3) {
        return "绝当商城轮播图";
      } else if (row.location === 4) {
        return "拍档头条";
      } else if (row.location === 5) {
        return "关键词搜索";
      } else if (row.location === 6) {
        return "首页";
      } else if (row.location === 7) {
        return "c端个人中心";
      } else if (row.location === 8) {
        return "商家入驻引导页";
      } else if (row.location === 9) {
        return "机构端个人中心";
      } else if (row.location === 10) {
        return "商家端个人中心";
      }else if (row.location === 11) {
        return "首页底部";
      } else if (row.location === 12) {
        return "优惠券";
      }else {
        return "--";
      }
    },
     /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getAd(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改认证商城广告";
        var value = this.form.type;

      if (value === 1) {
        this.content1 = true;
        this.content2 = false;
        this.content3 = false;
        this.content4 = false;
        this.content6 = false;
      } else if (value === 2) {
        this.content1 = false;
        this.content2 = true;
        this.content3 = false;
        this.content4 = false;
        this.content6 = false;
      } else if (value === 3) {
        this.content1 = false;
        this.content2 = false;
        this.content3 = true;
        this.content4 = false;
        this.content6 = false;
      } else if (value === 4) {
        this.content1 = false;
        this.content2 = false;
        this.content3 = false;
        this.content4 = true;
        this.content6 = false;
      } else if (value === 6) {
        this.content1 = false;
        this.content2 = false;
        this.content3 = false;
        this.content4 = false;
        this.content6 = true;
      } else {
        this.content1 = false;
        this.content2 = false;
        this.content3 = false;
        this.content4 = false;
        this.content6 = false;
      }
      });
    },
    selectType(value) {
      console.log("value: " + value);
      if (value === 1) {
        this.content1 = true;
        this.content2 = false;
        this.content3 = false;
        this.content4 = false;
        this.content6 = false;
      } else if (value === 2) {
        this.content1 = false;
        this.content2 = true;
        this.content3 = false;
        this.content4 = false;
        this.content6 = false;
      } else if (value === 3) {
        this.content1 = false;
        this.content2 = false;
        this.content3 = true;
        this.content4 = false;
        this.content6 = false;
      } else if (value === 4) {
        this.content1 = false;
        this.content2 = false;
        this.content3 = false;
        this.content4 = true;
        this.content6 = false;
      } else if (value === 6) {
        this.content1 = false;
        this.content2 = false;
        this.content3 = false;
        this.content4 = false;
        this.content6 = true;
      } else {
        this.content1 = false;
        this.content2 = false;
        this.content3 = false;
        this.content4 = false;
        this.content6 = false;
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
      this.content1=false;
      this.content2=false;
      this.content3=false;
      this.content4=false;
      this.content5=false;
      this.content6=false;
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
      this.title = "添加认证商城广告";
    },
   

    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
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
