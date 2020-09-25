<template>
  <ele-upload-video
    :fileSize="20"
    @error="handleUploadError"
    :responseFn="handleUploadSuccess"
    style="margin: 50px"
    action="uploadUrl"
    v-model="video"
  />
</template>
<script>
// import {policy,baseUrl} from '@/api/oss'

export default {
  name: "singleUpload",
  props: {
    value: String,
  },
  computed: {
    imageUrl() {
      return this.value;
    },
    imageName() {
      if (this.value != null && this.value !== "") {
        return this.value.substr(this.value.lastIndexOf("/") + 1);
      } else {
        return null;
      }
    },
    fileList() {
      return [
        {
          name: this.imageName,
          url: this.imageUrl,
        },
      ];
    },
    showFileList: {
      get: function () {
        return (
          this.value !== null && this.value !== "" && this.value !== undefined
        );
      },
      set: function (newValue) {},
    },
  },
  data() {
    return {
      dataObj: {
        // policy: '',
        signature: "",
        key: "",
        ossaccessKeyId: "",
        dir: "",
        host: "",
        // callback:'',
      },
      //是否显示进度条
      videoUploadPercent: "",
      //进度条的进度，
      isShowUploadVideo: false,
      uploadUrl: process.env.VUE_APP_BASE_API + "/common/fileUpload",
      dialogVisible: false,
    };
  },
  methods: {
    //进度条
    uploadVideoProcess(event, file, fileList) {
      this.dialogVisible = true;
      this.videoUploadPercent = file.percentage.toFixed(0) * 1;
    },
    emitInput(val) {
      this.$emit("input", val);
    },
    handleRemove(file, fileList) {
      this.emitInput("");
    },
    handlePreview(file) {
      this.dialogVisible = true;
    },
    handleUploadSuccess(res, file) {
      this.showFileList = true;
      this.dialogVisible = false;
      this.videoUploadPercent = 0;
      this.fileList.pop();
      console.log(res);
      this.fileList.push({ name: file.name, url: res.data.path });
      this.emitInput(this.fileList[0].url);
    },
  },
};
</script>
<style>
</style>


