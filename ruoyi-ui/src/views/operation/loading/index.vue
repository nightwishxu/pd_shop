<template>
  <div>
    <el-form
      ref="elForm"
      :model="formData"
      :rules="rules"
      size="medium"
      label-width="100px"
    >
      <el-form-item label="图片" prop="img">
        <single-upload
          v-model="formData.img"
          style="
            width: 350px;
            display: inline-block;
            margin-left: 10px;
            height: 600px;
          "
        ></single-upload>
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
        code: "welcome",
        img: null,
        value: null,
      },
      rules: {},
    };
  },
  computed: {},
  watch: {},
  created() {
    getCode("welcome").then((response) => {
      this.formData.code = response.data.code;
      var value = JSON.parse(response.data.value);
      this.formData.img = value.img;
    });
  },
  mounted() {},
  methods: {
    submitForm() {
      this.$refs["elForm"].validate((valid) => {
        if (!valid) return;
        // TODO 提交表单
        this.formData.value = JSON.stringify({ img: this.formData.img });
        updateCode(this.formData).then((response) => {
          getCode("welcome").then((response) => {
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
