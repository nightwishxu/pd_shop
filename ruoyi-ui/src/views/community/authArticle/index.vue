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
        label="标题"
        prop="title"
      >
        <el-input
          v-model="queryParams.title"
          placeholder="请输入标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item
        label="上架状态"
        prop="userReportStatus"
      >
        <el-select
          v-model="queryParams.isShow"
          placeholder="请选择"
          clearable
          size="small"
        >
          <el-option
            v-for="(item, index) in isShowOptions"
            :key="index"
            :label="item.label"
            :value="item.value"
            :disabled="item.disabled"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="发布时间">
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
          v-hasPermi="['system:article:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:article:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:article:remove']"
        >删除</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:article:export']"
        >导出</el-button> -->
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
      :data="articleList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column
        type="selection"
        width="55"
        align="center"
      />
      <el-table-column
        label="封面图片"
        align="center"
        prop="cover"
      >
        <template scope="scope">
          <img
            :src="scope.row.cover"
            width="40"
            height="40"
          />
        </template>
      </el-table-column>
      <el-table-column
        label="标题"
        align="center"
        prop="title"
        show-overflow-tooltip="true"
      />

      <el-table-column
        label="最后修改时间"
        align="center"
        prop="moifyTime"
        width="180"
      >
        <template slot-scope="scope">{{
        scope.row.createTime | dateYMDHMSFormat
      }}</template>
      </el-table-column>

      <el-table-column
        label="内容"
        align="center"
        prop="content"
        show-overflow-tooltip="true"
      />

      <!-- <el-table-column
        label="图片"
        align="center"
        prop="imgs"
      >
        <template scope="scope">
          <div v-if="scope.row.imgs">
            <span
              v-for="(item, index) in scope.row.imgs.split(',')"
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
      </el-table-column> -->

      <el-table-column
        label="状态"
        align="center"
        prop="isShow"
        :formatter="handleIsShow"
      />

      <el-table-column
        label="参与人数"
        align="center"
        prop="clickCount"
      />

      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            v-if="scope.row.isShow==1"
            type="text"
            icon="el-icon-edit"
            @click="chargeIsShow(scope.row)"
            v-hasPermi="['system:article:edit']"
          >下架</el-button>
          <el-button
            size="mini"
            type="text"
            v-if="scope.row.isShow==0"
            icon="el-icon-delete"
            @click="chargeIsShow(scope.row)"
            v-hasPermi="['system:article:remove']"
          >上架</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:article:edit']"
          >修改</el-button>

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

    <!-- 添加或修改动态对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="open"
      width="800px"
      append-to-body
    >
      <el-form
        ref="form"
        :model="form"
        :rules="rules"
        label-width="80px"
      >
        <el-form-item
          label="封面"
          prop="cover"
        >
          <single-upload
            v-model="form.cover"
            style="width: 300px; display: inline-block; margin-left: 10px"
          ></single-upload>
        </el-form-item>

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
          label="内容"
          prop="content"
        >
          <el-input
            type="textarea"
            v-model="form.content"
            placeholder="请输入内容"
          />
        </el-form-item>

        <el-form-item label="图片">
          <multi-upload
            ref="upload"
            v-model="imgfileList"
          ></multi-upload>
        </el-form-item>

        <el-form-item
          label="排序"
          prop="sort"
        >
          <el-input
            v-model="form.sort"
            placeholder="请输入排序"
          />
        </el-form-item>
        <el-form-item
          label="公告描述"
          prop="description"
        >
          <el-input
            v-model="form.description"
            type="textarea"
            placeholder="请输入内容"
          />
        </el-form-item>
        <!-- <el-form-item label="1 富文本 2其它动态 3 商品 4 h5链接">
          <el-select
            v-model="form.refType"
            placeholder="请选择1 富文本 2其它动态 3 商品 4 h5链接"
          >
            <el-option
              label="请选择字典生成"
              value=""
            />
          </el-select>
        </el-form-item> -->
        <!-- <el-form-item
          label="链接地址"
          prop="refUrl"
        >
          <el-input
            v-model="form.refUrl"
            placeholder="请输入链接地址"
          />
        </el-form-item> -->
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
import { listArticle, getArticle, delArticle, addArticle, updateArticle, exportArticle } from "@/api/community/article";
import SingleUpload from "@/components/Upload/singleUpload";
import MultiUpload from "@/components/Upload/multiUpload";

export default {
  name: "Article",
  components: { SingleUpload,MultiUpload },
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
      createTimeRange:null,
      // 动态表格数据
      articleList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        cover: null,
        type: null,
        status: null,
        isShow: null,
        title: null,
        content: null,
        imgs: null,
        video: null,
        commentCount: null,
        collectCount: null,
        praiseCount: null,
        clickCount: null,
        reportCount: null,
        labels: null,
        releaseTime: null,
        createName: null,
        updateName: null,
        sort: null,
        description: null,
        startTime: null,
        endTime: null,
        refType: null,
        userReportStatus:null,
        refUrl: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        status: [
          { required: true, message: "0删除1草稿2发布不能为空", trigger: "blur" }
        ],
      },
      isShowOptions: [
        {
          label: "全部",
          value: null,
        },
        {
          label: "下架",
          value: 0,
        },
        {
          label: "上架",
          value: 1,
        },
       ],

    };
  },
    computed: {
    imgfileList: {
      get: function () {
        let pics = [];
        // if(this.form.img===undefined||this.form.img==null||this.form.img===''){
        //   return pics;
        // }
        // pics.push(this.value.img);
        if (
          this.form.imgs === undefined ||
          this.form.imgs == null ||
          this.form.imgs === ""
        ) {
          return pics;
        }
        let albumPics = this.form.imgs.split(",");
        for (let i = 0; i < albumPics.length; i++) {
          pics.push(albumPics[i]);
        }
        return pics;
      },
      set: function (newValue) {
        if (newValue == null || newValue.length === 0) {
          // this.form.img = null;
          this.form.imgs = null;
        } else {
          // this.form.img = newValue[0];
          console.info("type = " + Object.prototype.toString.call(newValue));
          //  var values = newValue.split(",");
          //  if(values==null || values.length===0){
          //     this.form.imgs = null;
          //  }
          console.info("imgs start " + this.form.imgs);
          console.info("imgs newValue " + newValue);

          this.form.imgs = "";
          if (newValue.length > 1) {
            for (let i = 0; i < newValue.length; i++) {
              this.form.imgs += newValue[i];
              if (i !== newValue.length - 1) {
                this.form.imgs += ",";
              }
            }
          } else {
            this.form.imgs = newValue[0];
          }
          console.info("imgs " + this.form.imgs);
        }
      },
    },
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询动态列表 */
    getList() {
      this.loading = true;
      this.queryParams.type=2;
      if(this.createTimeRange!=undefined && this.createTimeRange!=null){
        this.queryParams.startTime = this.createTimeRange[0];
        this.queryParams.endTime = this.createTimeRange[1];
      }
      listArticle(this.queryParams).then(response => {
        this.articleList = response.rows;
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
        cover: null,
        type: null,
        status: 0,
        isShow: null,
        title: null,
        content: null,
        imgs: null,
        video: null,
        commentCount: null,
        collectCount: null,
        praiseCount: null,
        clickCount: null,
        reportCount: null,
        labels: null,
        releaseTime: null,
        createName: null,
        createTime: null,
        updateName: null,
        updateTime: null,
        sort: 9,
        description: null,
        refType: null,
        refUrl: null
      };
      this.resetForm("form");
    },
    handleIsShow(row, column) {
      if(row.isShow==1 ){
        return '上架'
      }else{
        return '下架'
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加动态";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getArticle(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改动态";
      });
    },
    chargeIsShow(row){
       updateArticle({"id":row.id,isShow:row.isShow==1?0:1}).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        this.form.type=2
        this.form.status=2
        this.form.isShow
        if (valid) {
          if (this.form.id != null) {
            updateArticle(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addArticle(this.form).then(response => {
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
      this.$confirm('是否确认删除动态编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delArticle(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有动态数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportArticle(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
