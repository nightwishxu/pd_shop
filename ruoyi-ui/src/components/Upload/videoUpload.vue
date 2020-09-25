<template>
  <div>
    <el-upload
      class="avatar-uploader"
      :action="uploadUrl"
      list-type="picture"
      :multiple="false"
      :show-file-list="false"
      :file-list="fileList"
      :on-remove="handleRemove"
      :on-progress="uploadVideoProcess"
      :on-success="handleUploadSuccess"
    >
      <el-button size="small" type="primary">点击上传</el-button>
      <div slot="tip" class="el-upload__tip">只能上传mp4文件，且不超过10MB</div>
    </el-upload>

    <video
      v-if="fileList[0].url!='' && fileList[0].url!=null && fileList[0].url!=undifined"
      :src="fileList[0].url"
      class="avatar"
      controls="controls"
    ></video>
    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
    <!-- <p v-if='load'> 上传中</p> -->
    <P style="color:#000000">请保证视频格式正确，且不超过20M</P>
    <!-- <video
      v-if="fileList[0].url!='' && fileList[0].url!=null && fileList[0].url!=undifined && !dialogVisible"
      class="avatar video-avatar"
      controls="controls"
      :src="fileList[0].url"
    >您的浏览器不支持视频播放</video>
    <i
      v-else-if="fileList[0].url =='' && fileList[0].url!=null && fileList[0].url!=undifined && !dialogVisible"
      class="el-icon-plus avatar-uploader-icon"
    ></i>
    <el-progress
      v-if="dialogVisible == true"
      type="circle"
      v-bind:percentage="videoUploadPercent"
      style="margin-top:7px;"
    ></el-progress>-->
  </div>
</template>
<script>
// import {policy,baseUrl} from '@/api/oss'

export default {
  name: "videoUpload",
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


