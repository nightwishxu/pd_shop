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
          v-hasPermi="['system:online:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:online:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:online:remove']"
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
            @click="showSearch = !showSearch"
          />
        </el-tooltip>
      </div>
    </el-row>

    <el-table
      v-loading="loading"
      :data="onlineList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column
        type="selection"
        width="55"
        align="center"
      />
      <el-table-column
        label="标题"
        align="center"
        prop="title"
      />
      <el-table-column
        columnlabel="封面"
        align="center"
        prop="img"
      >
        <template scope="scope">
          <el-popover
            placement="right"
            title
            trigger="click"
          >
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
        label="视频"
        align="center"
        prop="video"
      >
        <template slot-scope="scope">
          <el-button
            type="primary"
            v-if="scope.row.video"
            @click="playVideo(scope.row)"
            ref="btn"
            size="mini"
          >
            查看上传视频
          </el-button>
          <span v-else>未上传视频</span>
        </template>
      </el-table-column>

      </el-table-column>
      <el-table-column
        label="是否首页展示"
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

    <el-dialog
      title
      :visible.sync="videoOpen"
      width="40%"
      @close="closeDialog"
    >
      <video
        :src="videoUrl"
        class="avatar"
        preload="auto"
        controls="controls"
        width="100%"
      ></video>
    </el-dialog>

    <!-- 添加或修改在线视频对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="open"
      width="600px"
      append-to-body
    >
      <el-form
        ref="form"
        :model="form"
        :rules="rules"
        label-width="150px"
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
          label="是否设置为首页视频"
          prop="state"
        >
          <el-select
            v-model="form.state"
            placeholder="请选择下拉选择"
            clearable
            :style="{ width: '100%' }"
          >
            <el-option
              v-for="item in stateOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
              :disabled="item.disabled"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item
          label="封面"
          prop="img"
        >
          <single-upload
            v-model="form.img"
            style="width: 300px; display: inline-block; margin-left: 10px"
          ></single-upload>
        </el-form-item>
        <el-form-item
          label="上传在线视频视频"
          prop="video"
        >
          <video-upload
            v-model="form.video"
            style="width: 300px; display: inline-block; margin-left: 10px"
          ></video-upload>
        </el-form-item>
        <el-form-item label="标签">
          <div>
            <el-input
              ref="label"
              v-model="labelValue"
              placeholder="标签"
              size="small"
              :style="{ width: '40%' }"
            />
            <el-button
              id="btn"
              @click="addLabels"
            >添加视频标签</el-button>
          </div>
          <draggable
            :list="attributeOptions"
            :animation="340"
            group="selectItem"
            handle=".option-drag"
          >
            <div
              v-for="(item, index) in labelsOptions"
              :key="index"
            >
              <div
                class="close-btn select-line-icon"
                @click="labelsOptions.splice(index, 1)"
              >
                {{item}}
                <i class="el-icon-remove-outline" />
              </div>

            </div>
          </draggable>

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
  listOnline,
  getOnline,
  delOnline,
  addOnline,
  updateOnline,
  exportOnline,
} from "@/api/video/online";
import SingleUpload from "@/components/Upload/singleUpload";
import VideoUpload from "@/components/Upload/videoUpload";

export default {
  name: "Online",
  components: { SingleUpload, VideoUpload },
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
      // 在线视频表格数据
      onlineList: [],
      // 弹出层标题
      title: "",
      labelValue:"",
      videoOpen: false,
      videoUrl: null,
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        img: null,
        video: null,
        viewCnt: null,
        state: null,
        isSuggest: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [{ required: true, message: "标题不能为空", trigger: "blur" }],
        img: [{ required: true, message: "封面不能为空", trigger: "blur" }],
        video: [{ required: true, message: "视频不能为空", trigger: "blur" }],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" },
        ],
      },
      labelsOptions:[],

      stateOptions: [
        { label: "否", value: 0 },
        { label: "是", value: 1 },
      ],
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询在线视频列表 */
    getList() {
      this.loading = true;
      listOnline(this.queryParams).then((response) => {
        this.onlineList = response.rows;
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
        img: null,
        video: null,
        viewCnt: null,
        state: null,
        createTime: null,
        isSuggest: null,
      };
      this.resetForm("form");
      this.labelValue="";
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    handleState(row, cloumn) {
      if (row.state == 0) {
        return "首页不显示";
      } else if (row.state == 1) {
        return "首页显示";
      }
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    playVideo(row) {
      this.videoOpen = true;
      this.videoUrl = row.video;
      // this.videoUrl =
      //   "http://baidu.paidangwang.net/admin/download?id=c379cda3f709491c867c39e8cd6cb6c4";
    },
     addLabels(){
      if(this.labelValue==null || this.labelValue==''){
        return
      }
      this.labelsOptions.push(this.labelValue)
      this.labelValue=''
    },
    setOptionValue(item, val) {
      item.value = isNumberStr(val) ? +val : val
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
      this.title = "添加在线视频";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getOnline(id).then((response) => {
        this.form = response.data;
        if(response.data.labels!=undefined && response.data.labels!=null){
          this.labelsOptions = response.data.labels.split(",")
        }
        this.open = true;
        this.title = "修改在线视频";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if(this.labelsOptions!=undefined){
            console.info(this.labelsOptions)
            this.form.labels = this.labelsOptions.join(",")
          }
          if (this.form.id != null) {
            updateOnline(this.form).then((response) => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addOnline(this.form).then((response) => {
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
        '是否确认删除在线视频编号为"' + ids + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return delOnline(ids);
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
      this.$confirm("是否确认导出所有在线视频数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return exportOnline(queryParams);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () {});
    },
  },
};
</script>
