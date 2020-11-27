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
        label="机构名称"
        prop="name"
      >
        <el-input
          v-model="queryParams.name"
          placeholder="请输入机构名称"
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
          v-hasPermi="['org:org:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['org:org:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['org:org:remove']"
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
      :data="orgList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column
        type="selection"
        width="55"
        align="center"
      />
      <el-table-column
        label="机构账号"
        align="center"
        prop="account"
      />
      <el-table-column
        label="机构名称"
        align="center"
        prop="name"
      />
      <el-table-column
        label="机构法人"
        align="center"
        prop="legalPerson"
      />
      <el-table-column
        label="注册资金"
        align="center"
        prop="registeredCapital"
      />
      <el-table-column
        label="机构地址"
        align="center"
        prop="adress"
      />
      <el-table-column
        label="工商许可证"
        align="center"
        prop="businessLicenseCode"
      />
      <el-table-column
        label="电话"
        align="center"
        prop="phone"
      />
      <!-- <el-table-column label="状态" align="center" prop="stateInfo" /> -->
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
      >
        <template slot-scope="scope">{{
          scope.row.createTime | dateYMDHMSFormat
        }}</template>
      </el-table-column>

      <el-table-column
        label="操作状态"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            v-if="scope.row.state == 2"
            @click="handleState(scope.row)"
          >审核</el-button>
          <el-button
            size="mini"
            type="text"
            v-else-if="scope.row.state == 1"
            @click="handleState(scope.row)"
          >启用</el-button>
          <el-button
            size="mini"
            type="text"
            v-else-if="scope.row.state == 0"
            @click="handleState(scope.row)"
          >禁用</el-button>
        </template>
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
          >修改</el-button>
        </template>
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            v-if="scope.row.id"
            @click="changePwds(scope.row)"
          >修改密码</el-button>
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

    <!-- 添加或修改机构对话框 -->
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
        label-width="120px"
      >
        <el-form-item
          label="账号"
          prop="account"
        >
          <el-input
            v-model="form.account"
            placeholder="请输入账号"
          />
        </el-form-item>
        <el-form-item
          label="机构名称"
          prop="name"
        >
          <el-input
            v-model="form.name"
            placeholder="请输入机构名称"
          />
        </el-form-item>
        <el-form-item
          label="机构法人"
          prop="legalPerson"
        >
          <el-input
            v-model="form.legalPerson"
            placeholder="请输入机构法人"
          />
        </el-form-item>
        <el-form-item
          label="注册资金"
          prop="adress"
        >
          <el-input
            v-model="form.registeredCapital"
            placeholder="请输入注册资金"
          />
        </el-form-item>
        <el-form-item
          label="机构地址"
          prop="adress"
        >
          <el-input
            v-model="form.adress"
            placeholder="请输入机构地址"
          />
        </el-form-item>
        <el-form-item
          label="工商许可证号"
          prop="adress"
        >
          <el-input
            v-model="form.businessLicenseCode"
            placeholder="请输入工商许可证号"
          />
        </el-form-item>

        <el-form-item
          label="身份证号码"
          prop="adresidCards"
        >
          <el-input
            v-model="form.idCard"
            placeholder="请输入身份证号"
          />
        </el-form-item>
        <el-form-item
          label="电话"
          prop="adress"
        >
          <el-input
            v-model="form.phone"
            placeholder="请输入电话"
          />
        </el-form-item>
        <el-form-item
          label="身份证正面"
          prop="idCardImg"
        >
          <single-upload
            v-model="form.idCardImg"
            style="width: 300px; display: inline-block; margin-left: 10px"
          ></single-upload>
        </el-form-item>
        <el-form-item
          label="身份证反面"
          prop="idCardReverse"
        >
          <single-upload
            v-model="form.idCardReverse"
            style="width: 300px; display: inline-block; margin-left: 10px"
          ></single-upload>
        </el-form-item>
        <el-form-item
          label="工商许可证"
          prop="businessLicense"
        >
          <single-upload
            v-model="form.businessLicense"
            style="width: 300px; display: inline-block; margin-left: 10px"
          ></single-upload>
        </el-form-item>
        <el-form-item
          label="典当许可证"
          prop="pawnExequatur"
        >
          <single-upload
            v-model="form.pawnExequatur"
            style="width: 300px; display: inline-block; margin-left: 10px"
          ></single-upload>
        </el-form-item>
        <el-form-item label="其他辅助文件">
          <!-- <el-input v-model="form.otherFile" placeholder="请输入图片" /> -->
          <multi-upload
            ref="upload"
            v-model="imgfileList"
          ></multi-upload>
        </el-form-item>
        <el-form-item
          label="店铺logo"
          prop="orgLogo"
        >
          <single-upload
            v-model="form.orgLogo"
            style="width: 300px; display: inline-block; margin-left: 10px"
          ></single-upload>
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

    <el-dialog
      :title="title1"
      :visible.sync="open1"
      width="500px"
      append-to-body
    >
      <el-form
        ref="form1"
        :model="form1"
        :rules="rules"
        label-width="120px"
      >
        <el-form-item
          label="新密码"
          prop="password"
        >
          <el-input
            v-model="form1.password"
            placeholder="请输入新密码"
          />
        </el-form-item>
      </el-form>

      <div
        slot="footer"
        class="dialog-footer"
      >
        <el-button
          type="primary"
          @click="submit1Form"
        >确 定</el-button>
        <el-button @click="cancel1">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listOrg,
  getOrg,
  delOrg,
  addOrg,
  updateOrg,
  exportOrg,
  changeState,
  changePwds,
  saveService,
  pawnMsg,
} from "@/api/org/org";
import SingleUpload from "@/components/Upload/singleUpload";
import MultiUpload from "@/components/Upload/multiUpload";

export default {
  name: "Org",
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
      // 机构表格数据
      orgList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        roleCode: null,
        type: null,
        account: null,
        password: null,
        name: null,
        legalPerson: null,
        registeredCapital: null,
        adress: null,
        businessLicenseCode: null,
        businessLicense: null,
        expirationRemindType: null,
        phone: null,
        balance: null,
        modifyTime: null,
        state: null,
        pawnState: null,
        overdueRate: null,
        seal: null,
        introduction: null,
        orgImages: null,
        orgLogo: null,
        redeemOverrate: null,
      },
      // 表单参数
      form: {},
      form1: {},
      // 表单校验
      rules: {
        roleCode: [
          { required: true, message: "角色不能为空", trigger: "blur" },
        ],
        createTime: [
          { required: true, message: "角色不能为空", trigger: "blur" },
        ],
      },
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
          this.form.otherFile === undefined ||
          this.form.otherFile == null ||
          this.form.otherFile === ""
        ) {
          return pics;
        }
        let albumPics = this.form.otherFile.split(",");
        for (let i = 0; i < albumPics.length; i++) {
          pics.push(albumPics[i]);
        }
        return pics;
      },
      set: function (newValue) {
        if (newValue == null || newValue.length === 0) {
          // this.form.img = null;
          this.form.otherFile = null;
        } else {
          // this.form.img = newValue[0];
          console.info("type = " + Object.prototype.toString.call(newValue));
          //  var values = newValue.split(",");
          //  if(values==null || values.length===0){
          //     this.form.otherFile = null;
          //  }
          console.info("otherFile start " + this.form.otherFile);
          console.info("otherFile newValue " + newValue);

          this.form.otherFile = "";
          if (newValue.length > 1) {
            for (let i = 0; i < newValue.length; i++) {
              this.form.otherFile += newValue[i];
              if (i !== newValue.length - 1) {
                this.form.otherFile += ",";
              }
            }
          } else {
            this.form.otherFile = newValue[0];
          }
          console.info("otherFile " + this.form.otherFile);
        }
      },
    },
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询机构列表 */
    getList() {
      this.loading = true;
      this.queryParams.type = 1;
      listOrg(this.queryParams).then((response) => {
        this.orgList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    cancel1() {
      this.open1 = false;
      this.reset1();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        roleCode: null,
        type: null,
        account: null,
        password: null,
        name: null,
        legalPerson: null,
        registeredCapital: null,
        adress: null,
        businessLicenseCode: null,
        businessLicense: null,
        expirationRemindType: null,
        phone: null,
        balance: null,
        createTime: null,
        modifyTime: null,
        state: null,
        pawnState: null,
        overdueRate: null,
        seal: null,
        introduction: null,
        orgImages: null,
        orgLogo: null,
        redeemOverrate: null,
        idCard: null,
        businessLicense: null,
        idCardImg: null,
        idCardReverse: null,
        pawnExequatur: null,
        otherFile: null,
      };
      this.resetForm("form");
    },
    reset1() {
      this.form1 = {
        id: null,
        password: null,
      };
      this.resetForm("form1");
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
      this.title = "添加机构";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getOrg(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改机构";
      });
    },
    handleState(row) {
      changeState({ id: row.id, v: row.state }).then((response) => {
        this.getList();
      });
    },
    // changePwds
    changePwds(row) {
      this.reset1();
      this.form1.id = row.id;
      this.open1 = true;
      this.title1 = "修改密码";

      // changePwds({ id: row.id }).then((response) => {
      //   this.getList();
      // });
    },
    submit1Form() {
      changePwds(this.form1).then((response) => {
        this.msgSuccess("修改成功");
        this.open1 = false;
        this.getList();
      });
    },

    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        this.form.type = 1;
        if (valid) {
          if (this.form.id != null) {
            updateOrg(this.form).then((response) => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addOrg(this.form).then((response) => {
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
      this.$confirm('是否确认删除机构编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return delOrg(ids);
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
      this.$confirm("是否确认导出所有机构数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return exportOrg(queryParams);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () {});
    },
  },
};
</script>
