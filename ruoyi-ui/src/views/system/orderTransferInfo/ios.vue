<template>
  <div style="width: 600px">
    <el-form
      ref="elForm"
      :model="formData"
      :rules="rules"
      size="medium"
      label-width="100px"
    >
      <el-form-item
        label="收款人"
        prop="bankCardUserName"
        required
      >
        <el-input v-model="formData.bankCardUserName" />
      </el-form-item>
      <el-form-item
        label="账户"
        prop="bankCardNo"
        required
      >
        <el-input v-model="formData.bankCardNo" />
      </el-form-item>
      <el-form-item
        label="开户行"
        prop="bankCardNname"
        required
      >
        <el-input v-model="formData.bankCardNname" />
      </el-form-item>
      <!-- <el-form-item label="apk包" prop="version">
        <el-input v-model="formData.version"  />
      </el-form-item> -->

      <el-form-item size="large">
        <el-button
          type="primary"
          @click="submitForm"
        >提交</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import SingleUpload from "@/components/Upload/singleUpload";
import { getCode, updateCode } from "@/api/system/code";

export default {
  components: { SingleUpload },
  props: [],
  data() {
    return {
      formData: {
        code: null,
        bankCardUserName: null,
        bankCardNo: null,
        bankCardNname: null
      },
      rules: {},
      updateEnabledOptions: [
        {
          label: "否",
          value: 0,
        },
        {
          label: "是",
          value: 1,
        },
      ],
    };
  },

  computed: {},
  watch: {},
  created() {
    getCode("orderTransferInfo").then((response) => {
      this.formData.code = response.data.code;
      var value = JSON.parse(response.data.value);
      // this.formData.version = value.version;
      this.formData.url = value.url;
      this.formData.miniVersion = value.miniVersion;
      this.formData.maxVersion = value.maxVersion;
      this.formData.appChargeLog = value.appChargeLog;
      this.formData.updateEnabled = value.updateEnabled;
    });
  },
  mounted() {},
  methods: {
    submitForm() {
      this.$refs["elForm"].validate((valid) => {
        if (!valid) return;
        // TODO 提交表单
        this.formData.value = JSON.stringify(this.formData);
        updateCode(this.formData).then((response) => {
          getCode("user@0").then((response) => {
            this.msgSuccess("修改成功");
            this.formData.code = response.data.code;
            var value = JSON.parse(response.data.value);
            this.formData.img = value.img;
          });
        });
      });
    },
    resetForm() {
      this.$refs["elForm"].resetFields();
    },
    field101BeforeUpload(file) {
      let isRightSize = file.size / 1024 / 1024 < 2;
      if (!isRightSize) {
        this.$message.error("文件大小超过 2MB");
      }
      return isRightSize;
    },
  },
};
</script>
<style>
.el-upload__tip {
  line-height: 1.2;
}
</style>
