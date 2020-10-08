<template>
  <div style="width: 600px">
    <el-form
      ref="elForm"
      :model="formData"
      :rules="rules"
      size="medium"
      label-width="100px"
    >
      <el-form-item label="版本" prop="version" required>
        <el-input v-model="formData.version" />
      </el-form-item>
      <el-form-item label="地址" prop="url" required>
        <el-input v-model="formData.url" />
      </el-form-item>
      <!-- <el-form-item label="apk包" prop="version">
        <el-input v-model="formData.version"  />
      </el-form-item> -->
      <el-form-item label="强制更新" prop="isFlag" required>
        <el-select v-model="formData.isFlag" :style="{ width: '100%' }">
          <el-option
            v-for="item in isFlagOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="更新内容" prop="info" required>
        <el-input v-model="formData.info" type="textarea" rows="3" />
      </el-form-item>
      <el-form-item size="large">
        <el-button type="primary" @click="submitForm">提交</el-button>
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
        version: null,
        url: null,
        isFlag: null,
        info: null,
        value: null,
      },
      rules: {},
      isFlagOptions: [
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
    getCode("ios_org@sys").then((response) => {
      this.formData.code = response.data.code;
      var value = JSON.parse(response.data.value);
      this.formData.version = value.version;
      this.formData.url = value.url;
      this.formData.info = value.info;
      this.formData.isFlag = value.isFlag;
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
          getCode("ios_org@sys").then((response) => {
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
