<template>
	<div class="homework-add-index">
		<el-form ref="form" :model="form" label-width="120px" :rules="rules">
			<el-form-item label="作业标题" prop="homework_title">
				<el-input v-model="form.homework_title"></el-input>
			</el-form-item>
			<el-form-item label="作业描述" prop="homework_desc">
				<el-input v-model="form.homework_desc"></el-input>
			</el-form-item>
			<el-form-item label="作业文件路径" prop="homework_file_path">
				<fileUpload style="margin-top: -2px;" isImg="file" @change="fileChange" buttonText="上传文件"
				 :showFileList="true" :fileList="fileList" :limit="2" listType="text"></fileUpload>
			</el-form-item>
		</el-form>
		<div style="text-align: right;">
			<el-button v-if="!form.id" type="primary" @click="go2Add">创建</el-button>
			<el-button v-if="form.id" type="primary" @click="go2Add">修改</el-button>
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
				fileList:[],
				form: {
					homework_title: "",
					homework_desc: "",
					publish_teacher: "",
					homework_file_path: "",
				},
				options: [],
				rules: {
					homework_title: [{
						required: true,
						message: '请输入作业标题',
						trigger: 'change'
					}, ],
					homework_desc: [{
						required: true,
						message: '请输入作业描述',
						trigger: 'change'
					}],
					homework_file_path: [{
						required: true,
						message: '请上传作业文件',
						trigger: 'change'
					}],
				}
			};
		},
		mounted() {
			if (this.rjDialogParams().data) {
				this.form = JSON.parse(JSON.stringify(this.rjDialogParams().data));
			}
			if (this.form.homework_file_path) {
				let temp = {};
				temp.name = this.form.homework_file_path.split("/")[this.form.homework_file_path.split("/").length - 1];
				temp.url = this.form.homework_file_path;
				this.fileList.push(temp);
			}
		},
		methods: {
			fileChange(res){
				this.form.homework_file_path = res;
			},
			go2Add() {
				this.$refs['form'].validate((valid) => {
					if (valid) {
						if (this.form.id) {
							this.$axios.put("/v1/homeworks/" + this.form.id, this.form).then((response) => {
								let message = response.data;
								this.$message.success('修改作业成功');
								this.closeRjDialog();
							}, (response) => {
								this.$message.error('修改作业失败');
							});
						} else {
							this.$axios.post("/v1/homeworks", this.form).then((response) => {
								let message = response.data;
								this.$message.success('添加作业成功');
								this.closeRjDialog();
							}, (response) => {
								this.$message.error('添加作业失败');
							});
						}
					} else {
						return false;
					}
				});
			},
			doCancel() {
				this.closeRjDialog();
			}
		},
	}
</script>

<style lang="scss">
	.homework-add-index {}
</style>
