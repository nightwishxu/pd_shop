<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="编号" prop="code">
        <el-input
          v-model="queryParams.code"
          placeholder="请输入编号"
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
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:certificate:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:certificate:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:certificate:remove']"
          >删除</el-button
        >
      </el-col>
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
      :data="certificateList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="证书编号" align="center" prop="code" />
      <el-table-column label="名称" align="center" prop="name" />
      <el-table-column label="尺寸(长*宽*高)(单位:cm)" align="center">
        <template slot-scope="scope">
          <span
            >{{ scope.row.length }}*{{ scope.row.width }}*{{
              scope.row.height
            }}</span
          >
        </template>
      </el-table-column>

      <el-table-column label="重量(单位:g)" align="center" prop="weight" />
      <el-table-column label="材质" align="center" prop="material" />

      <el-table-column label="图片概览" align="center" prop="imgs">
        <template scope="scope">
          <span v-for="(item, index) in scope.row.imgs.split(',')" :key="index">
            <el-popover placement="left" trigger="click" width="300">
              <el-image :src="item" />
              <el-image
                slot="reference"
                :src="item"
                :alt="item"
                style="max-height: 50px; max-width: 50px; padding: 5px"
              />
            </el-popover>
          </span>
        </template>
      </el-table-column>

      <el-table-column
        label="添加流通记录"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button size="mini" type="text" @click="handleForm1(scope.row)"
            >添加流通记录</el-button
          >
        </template>
      </el-table-column>

      <el-table-column
        label="详情"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button size="mini" type="text" @click="handleUpdate(scope.row)"
            >查看详情</el-button
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

    <!-- 添加或修改证书对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="850px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="名称" prop="name">
              <el-input v-model="form.name" placeholder="请输入名称" />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="编号" prop="code">
              <el-input v-model="form.code" placeholder="请输入编号" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="长  单位cm" prop="length">
              <el-input v-model="form.length" placeholder="请输入尺寸--长" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="宽  单位cm" prop="width">
              <el-input v-model="form.width" placeholder="请输入尺寸--宽" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="高  单位cm" prop="height">
              <el-input v-model="form.height" placeholder="请输入尺寸--高" />
            </el-form-item>
          </el-col>
          <el-col :span="12"
            ><el-form-item label="重量(单位g)" prop="weight">
              <el-input v-model="form.weight" placeholder="请输入重量" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="材质" prop="material">
              <el-input
                v-model="form.material"
                placeholder="请输入材质"
              /> </el-form-item
          ></el-col>
          <el-col :span="12">
            <el-form-item label="主体材质" prop="mainMaterial">
              <el-input
                v-model="form.mainMaterial"
                placeholder="请输入主体材质"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="其他辅材" prop="otherMaterial">
              <el-input
                v-model="form.otherMaterial"
                placeholder="请输入其他辅材"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="创作年代" prop="createYear">
              <el-input
                v-model="form.createYear"
                placeholder="请输入创作年代"
              /> </el-form-item
          ></el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="其他" prop="other">
              <el-input v-model="form.other" placeholder="请输入其他" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="图片">
          <!-- <el-input v-model="form.imgs" placeholder="请输入图片" /> -->
          <multi-upload ref="upload" v-model="imgfileList"></multi-upload>
        </el-form-item>
        <el-row>
          <el-col :span="12">
            <el-form-item label="市场流通性" prop="marketLiquidity">
              <el-select
                v-model="form.marketLiquidity"
                clearable
                :style="{ width: '100%' }"
              >
                <el-option
                  v-for="item in levelOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                  :disabled="item.disabled"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="价值稳定性" prop="valueStability">
              <el-select
                v-model="form.valueStability"
                clearable
                :style="{ width: '100%' }"
              >
                <el-option
                  v-for="item in levelOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                  :disabled="item.disabled"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="材质易损性" prop="materialVulnerability">
              <el-select
                v-model="form.materialVulnerability"
                clearable
                :style="{ width: '100%' }"
              >
                <el-option
                  v-for="item in levelOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                  :disabled="item.disabled"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="存放条件" prop="storageCondition">
              <el-input
                v-model="form.storageCondition"
                placeholder="请输入存放条件"
              /> </el-form-item
          ></el-col>
        </el-row>
        <el-row>
          <el-col :span="12"
            ><el-form-item label="肉眼可见缺陷" prop="nakedEyeDefect">
              <el-input
                v-model="form.nakedEyeDefect"
                placeholder="请输入肉眼可见缺陷"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="金融记录" prop="financeLog">
              <el-input
                v-model="form.financeLog"
                placeholder="请输入金融记录"
              /> </el-form-item
          ></el-col>
        </el-row>
        <el-form-item label="其他事项" prop="otherBusiness">
          <el-input v-model="form.otherBusiness" placeholder="请输入其他事项" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" v-if="btnFlag">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog
      :title="title1"
      :visible.sync="open1"
      width="500px"
      append-to-body
    >
      <el-form ref="form1" :model="form1" :rules="rules" label-width="80px">
        <el-form-item label="流通日期" prop="logTime">
          <el-date-picker
            clearable
            size="small"
            style="width: 300px"
            v-model="form1.logTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择其他事项"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="流通价格" prop="name">
          <el-input
            v-model="form1.price"
            placeholder="请输入流通价格"
            style="width: 300px"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm1">确 定</el-button>
        <el-button @click="cancel1">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listCertificate,
  getCertificate,
  delCertificate,
  addCertificate,
  updateCertificate,
  exportCertificate,
  saveCertificateLog,
  checkDetail,
} from "@/api/certificate/certificate";
import MultiUpload from "@/components/Upload/multiUpload";

export default {
  name: "Certificate",
  components: { MultiUpload },
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
      btnFlag: true,
      // 证书表格数据
      certificateList: [],
      // 弹出层标题
      title: "",
      title1: "",
      title2: "",
      // 是否显示弹出层
      open: false,
      open1: false,
      open2: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        code: null,
        length: null,
        width: null,
        height: null,
        weight: null,
        material: null,
        mainMaterial: null,
        otherMaterial: null,
        createYear: null,
        other: null,
        imgs: null,
        marketLiquidity: null,
        valueStability: null,
        materialVulnerability: null,
        storageCondition: null,
        nakedEyeDefect: null,
        financeLog: null,
        otherBusiness: null,
        modifyTime: null,
      },
      // 表单参数
      form: {},
      form1: {},
      form2: {},
      levelOptions: [
        { label: "一颗星", value: 1 },
        { label: "二颗星", value: 2 },
        { label: "三颗星", value: 3 },
        { label: "四颗星", value: 4 },
        { label: "五颗星", value: 5 },
      ],
      // 表单校验
      rules: {
        createTime: [
          { required: true, message: "其他事项不能为空", trigger: "blur" },
        ],
      },
    };
  },
  computed: {
    imgfileList: {
      get: function () {
        let pics = [];
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
    imgfileList2: {
      // get: function () {
      //   let pics = [];
      //   if (
      //     this.form2.imgs === undefined ||
      //     this.form2.imgs == null ||
      //     this.form2.imgs === ""
      //   ) {
      //     return pics;
      //   }
      //   let albumPics = this.form.imgs2.split(",");
      //   for (let i = 0; i < albumPics.length; i++) {
      //     pics.push(albumPics[i]);
      //   }
      //   return pics;
      // },
      // set: function (newValue) {
      //   if (newValue == null || newValue.length === 0) {
      //     // this.form.img = null;
      //     this.form.imgs = null;
      //   } else {
      //     // this.form.img = newValue[0];
      //     console.info("type = " + Object.prototype.toString.call(newValue));
      //     this.form.imgs = "";
      //     if (newValue.length > 1) {
      //       for (let i = 0; i < newValue.length; i++) {
      //         this.form2.imgs += newValue[i];
      //         if (i !== newValue.length - 1) {
      //           this.form2.imgs += ",";
      //         }
      //       }
      //     } else {
      //       this.form.imgs = newValue[0];
      //     }
      //     console.info("imgs " + this.form.imgs);
      //   }
      // },
    },
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询证书列表 */
    getList() {
      this.loading = true;
      listCertificate(this.queryParams).then((response) => {
        this.certificateList = response.rows;
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
    cancel2() {
      this.open2 = false;
      this.reset2();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        name: null,
        code: null,
        length: null,
        width: null,
        height: null,
        weight: null,
        material: null,
        mainMaterial: null,
        otherMaterial: null,
        createYear: null,
        other: null,
        imgs: null,
        marketLiquidity: null,
        valueStability: null,
        materialVulnerability: null,
        storageCondition: null,
        nakedEyeDefect: null,
        financeLog: null,
        otherBusiness: null,
        createTime: null,
        modifyTime: null,
      };
      this.resetForm("form");
    },
    reset1() {
      this.form1 = {
        certificateId: null,
        logTime: null,
        price: null,
      };
      this.resetForm("form1");
    },
    reset2() {
      this.form2 = {
        id: null,
        name: null,
        code: null,
        length: null,
        width: null,
        height: null,
        weight: null,
        material: null,
        mainMaterial: null,
        otherMaterial: null,
        createYear: null,
        other: null,
        imgs: null,
        marketLiquidity: null,
        valueStability: null,
        materialVulnerability: null,
        storageCondition: null,
        nakedEyeDefect: null,
        financeLog: null,
        otherBusiness: null,
        createTime: null,
        modifyTime: null,
      };
      this.resetForm("form2");
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
      this.btnFlag = true;
      this.open = true;
      this.title = "添加证书";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.btnFlag = false;
      const id = row.id || this.ids;
      getCertificate(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改证书";
      });
    },
    handleForm1(row) {
      this.reset1();
      this.form1.certificateId = row.id;
      this.open1 = true;
      this.title1 = "添加流通记录";
    },
    handleForm2(row) {
      this.reset2();
      const id = row.id || this.ids;
      getCertificate(id).then((response) => {
        this.form2 = response.data;
        this.open2 = true;
        this.title2 = "详情";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateCertificate(this.form).then((response) => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addCertificate(this.form).then((response) => {
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

    submitForm1() {
      saveCertificateLog(this.form1).then((response) => {
        if (response.code === 200) {
          this.msgSuccess("添加成功");
          this.open1 = false;
          this.getList();
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除证书编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return delCertificate(ids);
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
      this.$confirm("是否确认导出所有证书数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return exportCertificate(queryParams);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () {});
    },
  },
};
</script>
