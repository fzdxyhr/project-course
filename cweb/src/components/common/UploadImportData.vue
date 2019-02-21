<template>
  <div>
    <rj-dialog-contant max-height="650px">
      <div slot="content" class="upa-wrapper">
        <div><a class="upa-download-text" target="_blank" :href="downloadUrl">点此下载模版</a></div>
        <div class="upa-upload">
          <el-upload ref="upload" :action="uploadUrl" :before-upload="beforeUpload" :on-success="handleSuccess" :on-error="handleError" :accept="accept" show-file-list :limit="1">
            <el-button size="small" type="primary">点击选择文件并上传</el-button>
            <div slot="tip" class="el-upload__tip">提示：只能上传xls和xlsx文件</div>
          </el-upload>
        </div>
      </div>
      <div slot="footer-opt">
      </div>
    </rj-dialog-contant>
  </div>
</template>

<script type="text/javascript">
import rjDialogContant from "./dialogContant.vue";

export default {
  components: {
    rjDialogContant
  },
  data() {
    return {
      accept: ".xls,.xlsx",
      uploadUrl: "",
      downloadUrl: ""
    };
  },
  created() {
    const params = this.rjDialogParams() || {};
    this.uploadUrl = params.uploadUrl;
    this.downloadUrl = params.downloadUrl;
  },
  methods: {
    beforeUpload(file) {
      const isXls =
        file.type ===
          "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" ||
        file.type === "application/vnd.ms-excel";
      if (!isXls) {
        this.$message.error("只能上传xls，xlsx格式!");
      }
      return isXls;
    },
    handleSuccess(res, file) {
      this.$alert("恭喜您，导入成功。", "导入成功", {
        confirmButtonText: "好的",
        type: "success",
        callback: action => {
          this.$refs.upload.clearFiles();
          this.closeRjDialog();
        }
      });
    },
    handleError(err, file) {
      console.log(err);
      this.$alert("导入失败，请修改后重新导入。", "导入失败", {
        confirmButtonText: "关闭",
        type: "error",
        callback: action => {
          this.$refs.upload.clearFiles();
        }
      });
    }
  },
  watch: {}
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
.upa-wrapper {
  padding: 0 20px;
}
.upa-download-text {
  color: #4db3ff!important;
  cursor: pointer;
}
.upa-upload {
  padding-top: 20px;
}
</style>

