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
        label="最低版本"
        prop="miniVersion"
        required
      >
        <el-input v-model="formData.miniVersion" />
      </el-form-item>
      <el-form-item
        label="最高版本"
        prop="maxVersion"
        required
      >
        <el-input v-model="formData.maxVersion" />
      </el-form-item>
      <el-form-item
        label="地址"
        prop="url"
        required
      >
        <el-input v-model="formData.url" />
      </el-form-item>
      <!-- <el-form-item label="apk包" prop="version">
        <el-input v-model="formData.version"  />
      </el-form-item> -->
      <el-form-item
        label="强制更新"
        prop="updateEnabled"
        required
      >
        <el-select
          v-model="formData.updateEnabled"
          :style="{ width: '100%' }"
        >
          <el-option
            v-for="item in updateEnabledOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item
        label="更新内容"
        prop="appChargeLog"
        required
      >
        <el-input
          v-model="formData.appChargeLog"
          type="textarea"
          rows="3"
        />
      </el-form-item>
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
        maxVersion: null,
        miniVersion: null,
        url: null,
        updateEnabled: null,
        appChargeLog: null,
        value: null,
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
    getCode("org@1").then((response) => {
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
          getCode("org@1").then((response) => {
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
