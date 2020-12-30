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
        label="用户账号(手机号)"
        prop="account"
      >
        <el-input
          v-model="queryParams.account"
          placeholder="请输入用户账号(手机号)"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item
        label="用户昵称"
        prop="nickName"
      >
        <el-input
          v-model="queryParams.nickName"
          placeholder="请输入用户昵称"
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

    <!-- <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['users:user:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['users:user:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['users:user:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['users:user:export']"
        >导出</el-button>
      </el-col> -->
    <!-- <div class="top-right-btn">
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
      :data="userList"
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
        label="用户账号"
        align="center"
        prop="account"
      />
      <!-- <el-table-column label="密码" align="center" prop="password" /> -->
      <el-table-column
        label="用户昵称"
        align="center"
        prop="nickName"
      />
      <el-table-column
        label="头像"
        align="center"
        prop="headImg"
      >
        <!-- <img style="height: 80px" :src="scope.row.headImg"> -->
        <template scope="scope">
          <!-- <span>{{ parseTime(scope.row.createTime, '{yyyy}-{m}-{d}') }}</span> -->
          <img
            style="height: 80px"
            :src="scope.row.headImg"
          >

        </template>
      </el-table-column>

      <el-table-column
        label="手机号"
        align="center"
        prop="phone"
      />
      <el-table-column
        label="认证类型"
        align="center"
        prop="authType"
        :formatter="authTypeFormat"
      />

      <!-- <el-table-column label="身份证正面" align="center" prop="idCardImg" />
      <el-table-column label="身份证反面" align="center" prop="idCardReverse" />
      <el-table-column label="身份证证件地址" align="center" prop="idCardHand" />
      <el-table-column label="摇一摇" align="center" prop="headShake" />
      <el-table-column label="点点头" align="center" prop="headNod" />
      <el-table-column label="眨眼睛" align="center" prop="headEye" />
      <el-table-column label="身份证人脸是否绑定(0:未绑定 1绑定)" align="center" prop="isBind" />
      <el-table-column label="0普通用户1机构员工账号" align="center" prop="type" />
      <el-table-column label="机构id" align="center" prop="orgId" /> -->
      <el-table-column
        label="注册时间"
        align="center"
        prop="createTime"
        width="180"
      >
        <template slot-scope="scope">
          <!-- <span>{{ parseTime(scope.row.createTime, '{yyyy}-{m}-{d}') }}</span> -->
          {{scope.row.createTime|dateYMDHMSFormat}}
        </template>
      </el-table-column>

      <!-- <el-table-column label="完善资料1是0否" align="center" prop="balance" />
     
      <el-table-column label="完善资料1是0否" align="center" prop="credit" />
      <el-table-column label="完善资料1是0否" align="center" prop="loginTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.loginTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column> -->
      <!-- <el-table-column label="智齿token" align="center" prop="sobotToken" />
      <el-table-column label="智齿token" align="center" prop="lastSobotTokenTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.lastSobotTokenTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="融云token" align="center" prop="imToken" />
      <el-table-column label="微信openid" align="center" prop="wxOpenid" />
      <el-table-column label="QQopenid" align="center" prop="qqOpenid" /> -->
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
          >详细</el-button>
          <!--  v-hasPermi="['users:user:edit']" -->
          <!-- <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          >删除</el-button> -->
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

    <!-- 添加或修改用户对话框 -->
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
          label="用户账号"
          prop="account"
        >
          <!-- <el-input v-model="form.account" placeholder="请输入用户账号(手机号)" /> -->
          {{form.account}}
        </el-form-item>
        <!-- <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" placeholder="请输入密码" />
        </el-form-item> -->
        <el-form-item
          label="用户昵称"
          prop="nickName"
        >
          {{form.nickName}}
        </el-form-item>
        <el-form-item
          label="头像"
          prop="headImg"
        >
          {{form.headImg}}
        </el-form-item>
        <el-form-item label="性别">
          <div v-if="form.sex == 0">女</div>
          <div v-else-if="form.sex == 1">男</div>
          <div v-else-if="form.sex == 2">未知</div>
        </el-form-item>
        <el-form-item
          label="姓名"
          prop="name"
        >
          {{form.name}}
        </el-form-item>
        <el-form-item
          label="手机号"
          prop="phone"
        >
          {{form.phone}}
        </el-form-item>
        <el-form-item
          label="身份证"
          prop="idCard"
        >
          {{form.idCard}}
        </el-form-item>
        <el-form-item label="用户类别">
          <div v-if="form.type == 0">普通用户</div>
          <div v-else-if="form.type == 1">机构员工账号</div>
        </el-form-item>
        <el-form-item
          label="完善资料"
          prop="isComplete"
        >
          <div v-if="form.isComplete == 0">否</div>
          <div v-else-if="form.isComplete == 1">是</div>
        </el-form-item>
      </el-form>

    </el-dialog>
  </div>
</template>

<script>
import { listUser } from "@/api/auth/auth";

export default {
  name: "User",
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
      // 用户表格数据
      userList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        account: null,
        password: null,
        nickName: null,
        headImg: null,
        sex: null,
        name: null,
        phone: null,
        idCard: null,
        idCardImg: null,
        idCardReverse: null,
        idCardHand: null,
        headShake: null,
        headNod: null,
        headEye: null,
        isBind: null,
        type: null,
        orgId: null,
        modifyTime: null,
        isComplete: null,
        balance: null,
        state: null,
        credit: null,
        loginTime: null,
        sobotToken: null,
        lastSobotTokenTime: null,
        imToken: null,
        wxOpenid: null,
        qqOpenid: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        // account: [
        //   { required: true, message: "用户账号(手机号)不能为空", trigger: "blur" }
        // ],
        // type: [
        //   { required: true, message: "0普通用户1机构员工账号不能为空", trigger: "blur" }
        // ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询用户列表 */
    getList() {
      this.loading = true;
      listUser(this.queryParams).then(response => {
        this.userList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    isCompleteFormat(row,cloumn){
      if(row.isComplete === 1){
        return '是';
      }else {
        return '否';
      }
    },
    authTypeFormat(row,cloumn){
      //认证类型
      if(row.authType ==="1"){
        return '个人用户';
      }else if (row.authType ==="2"){
        return '企业用户';
      }else {
        return '未知';
      }
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        account: null,
        password: null,
        nickName: null,
        headImg: null,
        sex: null,
        name: null,
        phone: null,
        idCard: null,
        idCardImg: null,
        idCardReverse: null,
        idCardHand: null,
        headShake: null,
        headNod: null,
        headEye: null,
        isBind: null,
        type: null,
        orgId: null,
        createTime: null,
        modifyTime: null,
        isComplete: null,
        balance: null,
        state: null,
        credit: null,
        loginTime: null,
        sobotToken: null,
        lastSobotTokenTime: null,
        imToken: null,
        wxOpenid: null,
        qqOpenid: null
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
      this.title = "添加用户";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getUser(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "用户详情";
      });
    },
    /* 启用禁用用户 */
    handleForbid(row){
      const newId = row.id
      var entity = {id:newId,state:null};
      entity.state = row.state === 1?0:1;
      updateUser(entity).then(response => {
        if(response.code ===200){
           this.msgSuccess("修改成功");
          this.open = false;
          this.getList();
        }
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateUser(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addUser(this.form).then(response => {
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
      this.$confirm('是否确认删除用户编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delUser(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有用户数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportUser(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
