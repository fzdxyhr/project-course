<template>
	<div class="monitor-index">
		<el-form ref="form" :model="form" label-width="120px" :rules="rules">
			<el-form-item label="签到开始时间" prop="sign_start_time">
				<el-date-picker v-model="form.sign_start_time" value-format="yyyy-MM-dd HH:mm:ss" type="datetime" placeholder="选择签到开始时间">
				</el-date-picker>
			</el-form-item>
      <el-form-item label="签到结束时间" prop="sign_end_time">
      	<el-date-picker v-model="form.sign_end_time" value-format="yyyy-MM-dd HH:mm:ss" type="datetime" placeholder="选择签到结束时间">
      	</el-date-picker>
      </el-form-item>
		</el-form>
		<div style="text-align: right;">
			<el-button type="primary" @click="go2Add">确定</el-button>
			<el-button @click="doCancel">取消</el-button>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				form: {
					sign_start_time: "",
					sign_end_time: ""
				},
				options: [],
				rules: {
					sign_start_time: [{
						required: true,
						message: '请选择签到开始时间',
						trigger: 'change'
					}],
					sign_end_time: [{
						required: true,
						message: '请选择签到结束时间',
						trigger: 'change'
					}]
				}
			};
		},
		mounted() {
			if (this.rjDialogParams().data) {
				this.form = JSON.parse(JSON.stringify(this.rjDialogParams().data));
			}
		},
		methods: {
			go2Add() {
				this.$refs['form'].validate((valid) => {
					if (valid) {
						this.$axios.put("/v1/classes/" + this.form.id, this.form).then((response) => {
							let message = response.data;
							this.$message.success('设置签到时间成功');
							this.closeRjDialog();
						}, (response) => {
							this.$message.error('设置签到时间失败');
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
