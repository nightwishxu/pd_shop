<template> 
  <div>
    <el-upload
      :action="uploadUrl"
      list-type="picture-card"
      :file-list="fileList"
      ref="upload"
      :on-remove="handleRemove"
      :on-success="handleUploadSuccess"
      :on-preview="handlePreview"
      :limit="maxCount"
      :on-exceed="handleExceed"
    >
      <i class="el-icon-plus"></i>
    </el-upload>
    <el-dialog :visible.sync="dialogVisible">
      <img width="100%" :src="dialogImageUrl" alt="">
    </el-dialog>
  </div>
</template>
<script>
  // import {policy} from '@/api/oss'

  export default {
    name: 'multiUpload',
    props: {
      //图片属性数组
      value: Array,
      // value: {
      //   type: Array,
      //   default: function(){
      //     return[];
      //   }
      // },
      //最大上传图片数量
      maxCount:{
        type:Number,
        default:5
      },
      url: process.env.VUE_APP_BASE_API
    },
    data() {
      return {
        dataObj: {
          // policy: '',
          signature: '',
          key: '',
          ossaccessKeyId: '',
          dir: '',
          host: ''
        },
          uploadUrl: process.env.VUE_APP_BASE_API +"/common/fileUpload",
        dialogVisible: false,
        dialogImageUrl:null
      };
    },
    computed: {
     
      fileList() {
        console.info("value " + JSON.stringify(this.value))
        let fileList=[];
        for(let i=0;i<this.value.length;i++){
          fileList.push({url:this.value[i]});
        }
        return fileList;
      }
    },
    methods: {
       clearFiles(){
        this.value = []
      },
      emitInput(fileList) {
        let value=[];
        for(let i=0;i<fileList.length;i++){
          value.push(fileList[i].url);
        }
        this.$emit('input', value)
      },
      handleRemove(file, fileList) {
        this.emitInput(fileList);
      },
      handlePreview(file) {
        this.dialogVisible = true;
        this.dialogImageUrl=file.url;
      },
      handleUploadSuccess(res, file) {
        this.fileList.push({url: file.name,url:res.data.path});
        this.emitInput(this.fileList);
      },
      handleExceed(files, fileList) {
        this.$message({
          message: '最多只能上传'+this.maxCount+'张图片',
          type: 'warning',
          duration:1000
        });
      },
    }
  }
</script>
<style>

</style>


