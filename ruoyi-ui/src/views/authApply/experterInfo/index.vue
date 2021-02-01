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
        label="专家姓名"
        prop="title"
      >
        <el-input
          v-model="queryParams.experterName"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item
        label="商品名称"
        prop="title"
      >
        <el-input
          v-model="queryParams.goodsName"
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
      :data="infoList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column
        type="selection"
        width="55"
        align="center"
      />
      <el-table-column
        label="专家姓名"
        align="center"
        prop="experterName"
      />
      <el-table-column
        label="商品名称"
        align="center"
        prop="goodsName"
      />
      <el-table-column
        label="宝贝图片"
        align="center"
        prop="images"
      >
        <template scope="scope">
          <div v-if="scope.row.images">
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
          </div>

        </template>
      </el-table-column>
      <el-table-column
        label="评定结果"
        align="center"
        prop="state"
        :formatter="handleState"
      />
      <el-table-column
        label="专家意见"
        align="center"
        prop="info"
      />
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
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

    <!-- 添加或修改专家意见日志记录对话框 -->
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
          label="标题"
          prop="title"
        >
          <el-input
            v-model="form.title"
            placeholder="请输入标题"
          />
        </el-form-item>
        <el-form-item
          label="专家id"
          prop="experterId"
        >
          <el-input
            v-model="form.experterId"
            placeholder="请输入专家id"
          />
        </el-form-item>
        <el-form-item
          label="商品id"
          prop="goodsId"
        >
          <el-input
            v-model="form.goodsId"
            placeholder="请输入商品id"
          />
        </el-form-item>
        <el-form-item
          label="专家是否已经评定 0未评定 1已评定"
          prop="state"
        >
          <el-input
            v-model="form.state"
            placeholder="请输入专家是否已经评定 0未评定 1已评定"
          />
        </el-form-item>
        <el-form-item
          label="专家意见"
          prop="info"
        >
          <el-input
            v-model="form.info"
            type="textarea"
            placeholder="请输入内容"
          />
        </el-form-item>
        <el-form-item
          label="名称"
          prop="name"
        >
          <el-input
            v-model="form.name"
            placeholder="请输入名称"
          />
        </el-form-item>
        <el-form-item
          label="尺寸"
          prop="cc"
        >
          <el-input
            v-model="form.cc"
            placeholder="请输入尺寸"
          />
        </el-form-item>
        <el-form-item
          label="重量"
          prop="weight"
        >
          <el-input
            v-model="form.weight"
            placeholder="请输入重量"
          />
        </el-form-item>
        <el-form-item
          label="主材质"
          prop="mainMaterial"
        >
          <el-input
            v-model="form.mainMaterial"
            placeholder="请输入主材质"
          />
        </el-form-item>
        <el-form-item
          label="副材质"
          prop="otherMaterial"
        >
          <el-input
            v-model="form.otherMaterial"
            placeholder="请输入副材质"
          />
        </el-form-item>
        <el-form-item
          label="年代"
          prop="createYear"
        >
          <el-input
            v-model="form.createYear"
            placeholder="请输入年代"
          />
        </el-form-item>
        <el-form-item
          label="市场流通性"
          prop="marketCirculation"
        >
          <el-input
            v-model="form.marketCirculation"
            placeholder="请输入市场流通性"
          />
        </el-form-item>
        <el-form-item
          label="价值稳定性"
          prop="stabilityOfPrice"
        >
          <el-input
            v-model="form.stabilityOfPrice"
            placeholder="请输入价值稳定性"
          />
        </el-form-item>
        <el-form-item
          label="材质易损性"
          prop="materialVulnerable"
        >
          <el-input
            v-model="form.materialVulnerable"
            placeholder="请输入材质易损性"
          />
        </el-form-item>
        <el-form-item
          label="鉴定托低价"
          prop="experterPrice"
        >
          <el-input
            v-model="form.experterPrice"
            placeholder="请输入鉴定托低价"
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
import {
  listInfo,
  getInfo,
  delInfo,
  addInfo,
  updateInfo,
  exportInfo,
} from "@/api/authApply/experterInfo";

export default {
  name: "Info",
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
      // 专家意见日志记录表格数据
      infoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        experterId: null,
        goodsId: null,
        state: null,
        info: null,
        name: null,
        cc: null,
        weight: null,
        mainMaterial: null,
        otherMaterial: null,
        createYear: null,
        marketCirculation: null,
        stabilityOfPrice: null,
        goodsName: null,
        experterName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        experterId: [
          { required: true, message: "专家id不能为空", trigger: "blur" },
        ],
        goodsId: [
          { required: true, message: "商品id不能为空", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询专家意见日志记录列表 */
    getList() {
      this.loading = true;
      listInfo(this.queryParams).then((response) => {
        this.infoList = response.rows;
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
        title: null,
        experterId: null,
        goodsId: null,
        state: null,
        info: null,
        name: null,
        createTime: null,
        cc: null,
        weight: null,
        mainMaterial: null,
        otherMaterial: null,
        createYear: null,
        marketCirculation: null,
        stabilityOfPrice: null,
        materialVulnerable: null,
        experterPrice: null,
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
      this.title = "添加专家意见日志记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getInfo(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改专家意见日志记录";
      });
    },
    handleState(row, column) {
      if (row.state == 0) {
        return "还未评定";
      } else if (row.state == 1) {
        return "已评定";
      }
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateInfo(this.form).then((response) => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addInfo(this.form).then((response) => {
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
        '是否确认删除专家意见日志记录编号为"' + ids + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return delInfo(ids);
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
      this.$confirm("是否确认导出所有专家意见日志记录数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return exportInfo(queryParams);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () {});
    },
  },
};
</script>
