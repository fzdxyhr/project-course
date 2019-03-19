<template>
	<div class="submit-index">
		<fileUpload isImg="file" @change="fileChange" buttonText="上传作业"
		 :showFileList="true" :fileList="fileList" :limit="2" listType="text"></fileUpload>
		 <div style="text-align: right;">
		 	<el-button type="primary" @click="go2Add">上传</el-button>
		 	<el-button @click="doCancel">取消</el-button>
		 </div>
	</div>
</template>

<script>
	import fileUpload from '@components/common/file-upload.vue';
	export default {
		components: {
			fileUpload
		},
		data() {
			return {
				form: {
					homework_id:"",
					homework_file_path:""
				},
        fileList:[]
			}
		},
		mounted(){
			if (this.rjDialogParams().id) {
				this.form.homework_id = this.rjDialogParams().id;
			}
      if (this.rjDialogParams().data) {
        let filePath = this.rjDialogParams().data.submit_homework_file_path;
        if (filePath) {
          let temp = {};
        	temp.name = filePath.split("/")[filePath.split("/").length - 1];
        	temp.url = filePath;
          this.fileList.push(temp);
        }
      }
		},
		methods: {
			go2Add(){
				this.$axios.post("/v1/homeworks/submit", this.form).then((response) => {
					let message = response.data;
					this.$message.success('提交作业成功');
					this.closeRjDialog();
				}, (response) => {
					this.$message.error('提交作业失败');
				});
			},
			fileChange(res) {
				this.form.homework_file_path = res;
			},
			doCancel() {
				this.closeRjDialog();
			}
		},
	}
	
</script>

<style>
</style>
