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
        label="内容"
        prop="content"
      >
        <el-input
          v-model="queryParams.content"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item
        label="举报状态"
        prop="isReport"
      >
        <el-select
          v-model="queryParams.isReport"
          placeholder="请选择"
          clearable
          size="small"
        >
          <el-option
            v-for="(item, index) in isReportOptions"
            :key="index"
            :label="item.label"
            :value="item.value"
            :disabled="item.disabled"
          ></el-option>
        </el-select>
      </el-form-item>
      <!-- <el-form-item
        label="举报状态 0未举报 1举报"
        prop="isReport"
      >
        <el-input
          v-model="queryParams.isReport"
          placeholder="请输入举报状态 0未举报 1举报"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
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
          v-hasPermi="['system:comment:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:comment:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:comment:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:comment:export']"
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
      :data="commentList"
      @selection-change="handleSelectionChange"
    >
      <!-- <el-table-column
        type="selection"
        width="55"
        align="center"
      />
      <el-table-column
        label="举报状态 0未举报 1举报"
        align="center"
        prop="id"
      />
      <el-table-column
        label="主题id"
        align="center"
        prop="articleId"
      />
      <el-table-column
        label="主题作者id"
        align="center"
        prop="authorId"
      />
      <el-table-column
        label="1为文章"
        align="center"
        prop="type"
      /> -->
      <el-table-column
        label="评论内容"
        align="center"
        prop="content"
      />

      <el-table-column
        label="昵称"
        align="center"
        prop="nickName"
      />

      <el-table-column
        label="被点赞"
        align="center"
        prop="likeNum"
      />
      <el-table-column
        label="被回复"
        align="center"
        prop="replyNum"
      />
      <!-- <el-table-column
        label="是否回复0没有，1已回复"
        align="center"
        prop="isReply"
        prop="isReply"
      /> -->
      <!-- <el-table-column
        label="评论状态，-1为删除，0为待审核，1为已发布"
        align="center"
        prop="status"
      />
      <el-table-column
        label="0隐藏1展示"
        align="center"
        prop="isShow"
      />
      <el-table-column
        label="0为普通回复，1为后台管理员回复,2作者回复"
        align="center"
        prop="isAuthor"
      />-->
      <el-table-column
        label="举报状态"
        align="center"
        prop="isReport"
        :formatter="handleIsReport"
      />
      <el-table-column
        label="评论时间"
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
            v-if="scope.row.isReport==1 && scope.row.isShow==0"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="updateIshow(scope.row)"
            v-hasPermi="['system:comment:edit']"
          >展示</el-button>
          <el-button
            v-if="scope.row.isReport==1 && scope.row.isShow==1"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="updateIshow(scope.row)"
            v-hasPermi="['system:comment:remove']"
          >隐藏</el-button>
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
          label="主题id"
          prop="articleId"
        >
          <el-input
            v-model="form.articleId"
            placeholder="请输入主题id"
          />
        </el-form-item>
        <el-form-item
          label="主题作者id"
          prop="authorId"
        >
          <el-input
            v-model="form.authorId"
            placeholder="请输入主题作者id"
          />
        </el-form-item>
        <el-form-item label="1为文章">
          <el-select
            v-model="form.type"
            placeholder="请选择1为文章"
          >
            <el-option
              label="请选择字典生成"
              value=""
            />
          </el-select>
        </el-form-item>
        <el-form-item
          label="评论内容"
          prop="content"
        >
          <el-input
            v-model="form.content"
            type="textarea"
            placeholder="请输入内容"
          />
        </el-form-item>
        <el-form-item
          label="评论者id，一般为会员表的id"
          prop="userId"
        >
          <el-input
            v-model="form.userId"
            placeholder="请输入评论者id，一般为会员表的id"
          />
        </el-form-item>
        <el-form-item
          label="冗余用户昵称"
          prop="nickname"
        >
          <el-input
            v-model="form.nickname"
            placeholder="请输入冗余用户昵称"
          />
        </el-form-item>
        <el-form-item
          label="冗余用户头像"
          prop="icon"
        >
          <el-input
            v-model="form.icon"
            placeholder="请输入冗余用户头像"
          />
        </el-form-item>
        <el-form-item
          label="是否置顶评论，1为置顶，0为不置顶"
          prop="isTop"
        >
          <el-input
            v-model="form.isTop"
            placeholder="请输入是否置顶评论，1为置顶，0为不置顶"
          />
        </el-form-item>
        <el-form-item
          label="是否为热评，1为热评"
          prop="isHot"
        >
          <el-input
            v-model="form.isHot"
            placeholder="请输入是否为热评，1为热评"
          />
        </el-form-item>
        <el-form-item
          label="评论被点赞的次数"
          prop="likeNum"
        >
          <el-input
            v-model="form.likeNum"
            placeholder="请输入评论被点赞的次数"
          />
        </el-form-item>
        <el-form-item
          label="评论被回复的次数"
          prop="replyNum"
        >
          <el-input
            v-model="form.replyNum"
            placeholder="请输入评论被回复的次数"
          />
        </el-form-item>
        <el-form-item
          label="是否回复0没有，1已回复"
          prop="isReply"
        >
          <el-input
            v-model="form.isReply"
            placeholder="请输入是否回复0没有，1已回复"
          />
        </el-form-item>
        <el-form-item label="评论状态，-1为删除，0为待审核，1为已发布">
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
          label="0为普通回复，1为后台管理员回复,2作者回复"
          prop="isAuthor"
        >
          <el-input
            v-model="form.isAuthor"
            placeholder="请输入0为普通回复，1为后台管理员回复,2作者回复"
          />
        </el-form-item>
        <el-form-item
          label="举报状态 0未举报 1举报"
          prop="isReport"
        >
          <el-input
            v-model="form.isReport"
            placeholder="请输入举报状态 0未举报 1举报"
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
import { listComment, getComment, delComment, addComment, updateComment, exportComment } from "@/api/community/articleComment";

export default {
  name: "Comment",
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
      commentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        articleId: null,
        authorId: null,
        type: null,
        content: null,
        userId: null,
        nickname: null,
        icon: null,
        isTop: null,
        isHot: null,
        likeNum: null,
        replyNum: null,
        isReply: null,
        status: null,
        isShow: null,
        isAuthor: null,
        isReport: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        type: [
          { required: true, message: "1为文章不能为空", trigger: "blur" }
        ],
        isTop: [
          { required: true, message: "是否置顶评论，1为置顶，0为不置顶不能为空", trigger: "blur" }
        ],
        isHot: [
          { required: true, message: "是否为热评，1为热评不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "评论状态，-1为删除，0为待审核，1为已发布不能为空", trigger: "blur" }
        ],
      },
      isReportOptions:[
        {
          label:"举报",
          value:1,
        },
        {
          label:"未举报",
          value:0,
        },
      ]
    };
  },
  created() {
    this.getList();
  },
  methods: {
      handleIsReport(row, column) {
      if( row.isReport==null ||row.isReport==0){
        return '未举报'
      }else{
        return '举报'
      }
    },
    btnClick() {
      const vm = this;
      vm.$router.push({path: '/community/article', params: { id: 10 }})
    },
    updateIshow(row){
      if(row.isShow==null || row.isShow==0){
          updateComment({"id":row.id,isShow:1}).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
      }else {
         updateComment({"id":row.id,isShow:0}).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
      }
    },
    /** 查询【请填写功能名称】列表 */
    getList() {
      this.loading = true;
      listComment(this.queryParams).then(response => {
        this.commentList = response.rows;
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
        articleId: null,
        authorId: null,
        type: null,
        content: null,
        userId: null,
        nickname: null,
        icon: null,
        isTop: null,
        isHot: null,
        likeNum: null,
        replyNum: null,
        isReply: null,
        status: 0,
        isShow: null,
        createTime: null,
        isAuthor: null,
        isReport: null
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
      getComment(id).then(response => {
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
            updateComment(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addComment(this.form).then(response => {
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
          return delComment(ids);
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
          return exportComment(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
