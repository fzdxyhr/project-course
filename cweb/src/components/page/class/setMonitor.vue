<template>
	<div class="monitor-index">
		<el-form ref="form" :model="form" label-width="80px" :rules="rules">
			<el-form-item label="班长" prop="monitor">
				<el-select v-model="form.monitor" placeholder="请选择">
					<el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
					</el-option>
				</el-select>
			</el-form-item>
		</el-form>
		<div style="text-align: right;">
			<el-button type="primary" @click="go2Add">设置</el-button>
			<el-button @click="doCancel">取消</el-button>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				form: {
					monitor: "",
				},
				options: [],
				rules: {
					monitor: [{
						required: true,
						message: '请选择班长',
						trigger: 'change'
					}]
				}
			};
		},
		mounted() {
			this.findTeachers();
			if (this.rjDialogParams().data) {
				this.form = JSON.parse(JSON.stringify(this.rjDialogParams().data));
			}
		},
		methods: {
			findTeachers() {
				this.$axios.get("/v1/users?page_no=1&page_size=10000&role=student").then((response) => {
					let message = response.data;
					this.options = [];
					message.items.forEach((item) => {
						let temp = {};
						temp.value = item.id;
						temp.label = item.user_name;
						this.options.push(temp);
					});
				}, (response) => {
					this.$message.error('获取学生失败');
				});
			},
			go2Add() {
				this.$refs['form'].validate((valid) => {
					if (valid) {
						this.$axios.put("/v1/classes/" + this.form.id, this.form).then((response) => {
							let message = response.data;
							this.$message.success('设置班长成功');
							this.closeRjDialog();
						}, (response) => {
							this.$message.error('设置班长失败');
						});
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
	.monitor-index {}
</style>
