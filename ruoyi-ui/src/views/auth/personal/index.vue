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
        label="店铺名称"
        prop="storeName"
      >
        <el-input
          v-model="queryParams.storeName"
          placeholder="请输入店铺名称"
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
      <!-- <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:personal:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:personal:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:personal:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:personal:export']"
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
      :data="personalList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column
        type="selection"
        width="55"
        align="center"
      />
      <el-table-column
        label="id"
        align="center"
        prop="id"
      />
      <el-table-column
        label="logo"
        align="center"
        prop="logo"
      >
        <template scope="scope">
          <el-popover
            placement="right"
            title
            trigger="click"
          >
            <el-image
              slot="reference"
              :src="scope.row.logo"
              :alt="scope.row.logo"
              style="max-height: 50px; max-width: 50px"
            ></el-image>
            <el-image :src="scope.row.logo"></el-image>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        label="店铺名称"
        align="center"
        prop="storeName"
      />
      <el-table-column
        label="店铺介绍"
        align="center"
        prop="storeIntroduce"
      />
      <el-table-column
        label="申请账号"
        align="center"
        prop="account"
      />
      <el-table-column
        label="姓名"
        align="center"
        prop="name"
      />
      <el-table-column
        label="身份证"
        align="center"
        prop="idCard"
      />
      <el-table-column
        label="手机号码"
        align="center"
        prop="phone"
      />

      <el-table-column
        prop="idCardFront"
        align="center"
        label="身份证照片正面"
      >
        <template scope="scope">
          <el-popover
            placement="right"
            title
            trigger="click"
          >
            <el-image
              slot="reference"
              :src="scope.row.idCardFront"
              :alt="scope.row.idCardFront"
              style="max-height: 50px; max-width: 50px"
            ></el-image>
            <el-image :src="scope.row.idCardFront"></el-image>
          </el-popover>
        </template>
      </el-table-column>

      <el-table-column
        prop="idCardBack"
        align="center"
        label="身份证照片反面"
      >
        <template scope="scope">
          <el-popover
            placement="right"
            title
            trigger="click"
          >
            <el-image
              slot="reference"
              :src="scope.row.idCardBack"
              :alt="scope.row.idCardBack"
              style="max-height: 50px; max-width: 50px"
            ></el-image>
            <el-image :src="scope.row.idCardBack"></el-image>
          </el-popover>
        </template>
      </el-table-column>

      <el-table-column
        prop="handIdCardFront"
        align="center"
        label="手持身份证正面"
      >
        <template scope="scope">
          <el-popover
            placement="right"
            title
            trigger="click"
          >
            <el-image
              slot="reference"
              :src="scope.row.handIdCardFront"
              :alt="scope.row.handIdCardFront"
              style="max-height: 50px; max-width: 50px"
            ></el-image>
            <el-image :src="scope.row.handIdCardFront"></el-image>
          </el-popover>
        </template>
      </el-table-column>

      <el-table-column
        label="审核状态"
        align="center"
        prop="state"
      >
        <template slot-scope="scope">
          <div v-if="scope.row.state==0">
            <el-button
              size="mini"
              type="text"
              @click="changeStateBtn(scope.row.id,1)"
            >通过</el-button>
            <el-button
              size="mini"
              type="text"
              @click="handleChangeState(scope.row)"
            >不通过</el-button>

          </div>
          <div v-if="scope.row.state==1">审核通过</div>
          <div v-if="scope.row.state==2">审核不通过</div>
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
            v-hasPermi="['system:personal:edit']"
          >查看</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:personal:remove']"
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

    <el-dialog
      :title="title1"
      :visible.sync="open1"
      width="400px"
      append-to-body
    >
      <el-form
        ref="form1"
        :model="form1"
        label-width="150px"
      >
        <el-form-item
          label="原因"
          prop="refuseInfo"
          id="refuseInfo"
        >
          <el-input
            v-model="form1.refuseInfo"
            placeholder="请输入原因"
          />
        </el-form-item>
      </el-form>
      <div
        slot="footer"
        class="dialog-footer"
      >
        <el-button
          type="primary"
          @click="changeStateBtnEx"
        >确 定</el-button>
        <el-button @click="cancel1">取 消</el-button>
      </div>
    </el-dialog>

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
          label="logo"
          prop="logo"
        >
          <single-upload
            v-model="form.logo"
            style="width: 300px; display: inline-block; margin-left: 10px"
          ></single-upload>
        </el-form-item>

        <el-form-item
          label="店铺名称"
          prop="storeName"
        >
          <el-input
            v-model="form.storeName"
            placeholder="请输入店铺名称"
          />
        </el-form-item>
        <el-form-item
          label="店铺介绍"
          prop="storeIntroduce"
        >
          <el-input
            v-model="form.storeIntroduce"
            type="textarea"
          />
        </el-form-item>
        <el-form-item
          label="姓名"
          prop="name"
        >
          <el-input v-model="form.name" />
        </el-form-item>
        <el-form-item
          label="身份证"
          prop="idCard"
        >
          <el-input v-model="form.idCard" />
        </el-form-item>
        <el-form-item
          label="手机号码"
          prop="phone"
        >
          <el-input
            v-model="form.phone"
            placeholder="请输入手机号码"
          />
        </el-form-item>

        <el-form-item
          label="身份证照片正面"
          prop="idCardFront"
        >
          <single-upload
            v-model="form.idCardFront"
            style="width: 300px; display: inline-block; margin-left: 10px"
          ></single-upload>
        </el-form-item>

        <el-form-item
          label="身份证照片反面"
          prop="idCardBack"
        >
          <single-upload
            v-model="form.idCardBack"
            style="width: 300px; display: inline-block; margin-left: 10px"
          ></single-upload>
        </el-form-item>
        <el-form-item
          label="审核不通过原因"
          prop="refuseInfo"
        >
          <el-input
            v-model="form.refuseInfo"
            placeholder="请输入审核不通过原因"
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
import { listPersonal, getPersonal, delPersonal, addPersonal, updatePersonal, exportPersonal,changeState } from "@/api/auth/personal";
import SingleUpload from "@/components/Upload/singleUpload";

export default {
  name: "Personal",
  components: { SingleUpload},
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
      personalList: [],
      // 弹出层标题
      title: "",
      title1: "",
      // 是否显示弹出层
      open: false,
      open1: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        logo: null,
        storeName: null,
        storeIntroduce: null,
        name: null,
        idCard: null,
        phone: null,
        idCardFront: null,
        idCardBack: null,
        createUser: null,
        state: null,
        refuseInfo: null,
        orgId: null
      },
      // 表单参数
      form: {},
      form1: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询【请填写功能名称】列表 */
    getList() {
      this.loading = true;
      listPersonal(this.queryParams).then(response => {
        this.personalList = response.rows;
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
        logo: null,
        storeName: null,
        storeIntroduce: null,
        name: null,
        idCard: null,
        phone: null,
        idCardFront: null,
        idCardBack: null,
        createUser: null,
        createTime: null,
        state: null,
        refuseInfo: null,
        orgId: null
      };
      this.resetForm("form");
    },
    reset1() {
      this.form1 = {
        id: null,
        refuseInfo: null,
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "查看";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getPersonal(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "查看";
      });
    },
    handleChangeState(row) {
        const id = row.id;
        this.form1 = { id: id, v: 2, refuseInfo: null };
        this.open1 = true;
        this.title1 = "审核不通过";
    },

    changeStateBtn(id,v) {
      var param = {"id":id,"v":v};
      console.info(param);
      changeState(param).then((response) => {
         this.msgSuccess("修改成功");
         this.getList();
      });
    },
    changeStateBtnEx(){
        changeState(this.form1).then((response) => {
        if (response.code === 200) {
          this.msgSuccess("修改成功");
          this.open1 = false;
          this.getList();
        }
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.open = false;
      this.getList();
      // this.$refs["form"].validate(valid => {
      //   if (valid) {
      //     if (this.form.id != null) {
      //       updatePersonal(this.form).then(response => {
      //         if (response.code === 200) {
      //           this.msgSuccess("修改成功");
      //           this.open = false;
      //           this.getList();
      //         }
      //       });
      //     } else {
      //       addPersonal(this.form).then(response => {
      //         if (response.code === 200) {
      //           this.msgSuccess("新增成功");
      //           this.open = false;
      //           this.getList();
      //         }
      //       });
      //     }
      //   }
      // });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除【请填写功能名称】编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delPersonal(ids);
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
          return exportPersonal(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
