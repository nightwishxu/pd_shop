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
        label="举报状态"
        prop="userReportStatus"
      >
        <el-select
          v-model="queryParams.userReportStatus"
          placeholder="请选择"
          clearable
          size="small"
        >
          <el-option
            v-for="(item, index) in userReportStatusOptions"
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

    <!-- <el-row :gutter="10" class="mb8">
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
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:article:export']"
        >导出</el-button>
      </el-col>
      <div class="top-right-btn">
        <el-tooltip class="item" effect="dark" content="刷新" placement="top">
          <el-button size="mini" circle icon="el-icon-refresh" @click="handleQuery" />
        </el-tooltip>
        <el-tooltip class="item" effect="dark" :content="showSearch ? '隐藏搜索' : '显示搜索'" placement="top">
          <el-button size="mini" circle icon="el-icon-search" @click="showSearch=!showSearch" />
        </el-tooltip>
      </div>
    </el-row> -->

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
        label="内容"
        align="center"
        prop="content"
        show-overflow-tooltip="true"
      />
      <el-table-column
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
      </el-table-column>
      <el-table-column
        label="举报状态"
        align="center"
        prop="userReportStatus"
        :formatter="handleUserReportStatus"
      />
      <el-table-column
        label="评论数"
        align="center"
        prop="commentCount"
      />
      <el-table-column
        label="点赞数"
        align="center"
        prop="praiseCount"
      />
      <el-table-column
        label="点击数"
        align="center"
        prop="clickCount"
      />
      <el-table-column
        label="发布时间"
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
          <div v-if="scope.row.userReportStatus>0">
            <el-button
              size="mini"
              v-if="scope.row.isShow==1"
              type="text"
              icon="el-icon-edit"
              @click="chargeIsShow(scope.row)"
              v-hasPermi="['system:article:edit']"
            >隐藏</el-button>
            <el-button
              size="mini"
              type="text"
              v-if="scope.row.isShow==0"
              icon="el-icon-delete"
              @click="chargeIsShow(scope.row)"
              v-hasPermi="['system:article:remove']"
            >信任</el-button>
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

    <!-- 添加或修改动态对话框 -->
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
          label="用户编号"
          prop="userId"
        >
          <el-input
            v-model="form.userId"
            placeholder="请输入用户编号"
          />
        </el-form-item>
        <el-form-item
          label="封面"
          prop="cover"
        >
          <el-input
            v-model="form.cover"
            placeholder="请输入封面"
          />
        </el-form-item>
        <el-form-item label="类型默认1用户帖子 2后台帖子">
          <el-select
            v-model="form.type"
            placeholder="请选择类型默认1用户帖子 2后台帖子"
          >
            <el-option
              label="请选择字典生成"
              value=""
            />
          </el-select>
        </el-form-item>
        <el-form-item label="0删除1草稿2发布">
          <el-radio-group v-model="form.status">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item
          label="0隐藏1展示"
          prop="isShow"
        >
          <el-input
            v-model="form.isShow"
            placeholder="请输入0隐藏1展示"
          />
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
          label="文章内容"
          prop="content"
        >
          <el-input
            v-model="form.content"
            placeholder="请输入文章内容"
          />
        </el-form-item>
        <el-form-item
          label="图片"
          prop="imgs"
        >
          <el-input
            v-model="form.imgs"
            type="textarea"
            placeholder="请输入内容"
          />
        </el-form-item>
        <el-form-item
          label="视频"
          prop="video"
        >
          <el-input
            v-model="form.video"
            type="textarea"
            placeholder="请输入内容"
          />
        </el-form-item>
        <el-form-item
          label="评论数"
          prop="commentCount"
        >
          <el-input
            v-model="form.commentCount"
            placeholder="请输入评论数"
          />
        </el-form-item>
        <el-form-item
          label="收藏数"
          prop="collectCount"
        >
          <el-input
            v-model="form.collectCount"
            placeholder="请输入收藏数"
          />
        </el-form-item>
        <el-form-item
          label="点赞数"
          prop="praiseCount"
        >
          <el-input
            v-model="form.praiseCount"
            placeholder="请输入点赞数"
          />
        </el-form-item>
        <el-form-item
          label="点击数"
          prop="clickCount"
        >
          <el-input
            v-model="form.clickCount"
            placeholder="请输入点击数"
          />
        </el-form-item>
        <el-form-item
          label="举报数"
          prop="reportCount"
        >
          <el-input
            v-model="form.reportCount"
            placeholder="请输入举报数"
          />
        </el-form-item>
        <el-form-item
          label="标签"
          prop="labels"
        >
          <el-input
            v-model="form.labels"
            placeholder="请输入标签"
          />
        </el-form-item>
        <el-form-item
          label="发布时间"
          prop="releaseTime"
        >
          <el-date-picker
            clearable
            size="small"
            style="width: 200px"
            v-model="form.releaseTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择发布时间"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item
          label="发布时间"
          prop="createName"
        >
          <el-input
            v-model="form.createName"
            placeholder="请输入发布时间"
          />
        </el-form-item>
        <el-form-item
          label="发布时间"
          prop="updateName"
        >
          <el-input
            v-model="form.updateName"
            placeholder="请输入发布时间"
          />
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
        <el-form-item label="1 富文本 2其它动态 3 商品 4 h5链接">
          <el-select
            v-model="form.refType"
            placeholder="请选择1 富文本 2其它动态 3 商品 4 h5链接"
          >
            <el-option
              label="请选择字典生成"
              value=""
            />
          </el-select>
        </el-form-item>
        <el-form-item
          label="1 富文本 2其它动态 3 商品 4 h5链接"
          prop="refUrl"
        >
          <el-input
            v-model="form.refUrl"
            placeholder="请输入1 富文本 2其它动态 3 商品 4 h5链接"
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
import { listArticle, getArticle, delArticle, addArticle, updateArticle, exportArticle } from "@/api/community/article";

export default {
  name: "Article",
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
      userReportStatusOptions: [
        {
          label: "全部",
          value: null,
        },
        {
          label: "未举报",
          value: 0,
        },
        {
          label: "举报",
          value: 1,
        },
       ],

    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询动态列表 */
    getList() {
      this.loading = true;
      this.queryParams.type=1;
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
        sort: null,
        description: null,
        refType: null,
        refUrl: null
      };
      this.resetForm("form");
    },
    handleUserReportStatus(row, column) {
      if(row.userReportStatus==0 ){
        return '未举报'
      }else{
        return '举报'
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
