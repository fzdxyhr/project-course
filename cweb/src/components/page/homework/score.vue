<template>
	<div class="score-index">
		<el-form ref="form" :model="form" label-width="80px" :rules="rules">
			<el-form-item label="作业评分" prop="score">
				<el-input v-model="form.score"></el-input>
			</el-form-item>
		</el-form>
		<div class="footer-group">
			<el-button type="primary" @click="onSubmit">提交</el-button>
			<el-button @click="doCancel">取消</el-button>
		</div>
	</div>
</template>

<script>
	var validateInteger = (rule, value, callback) => {
		var pattern = new RegExp("^[0-9]\\d*$");
		if (value && !pattern.test(value)) {
			callback(new Error('请输入整数'));
		}
		callback();
	};
	var validateIntegerLength = (rule, value, callback) => {
		var pattern = new RegExp("^[0-9]\\d*$");
		if (!value && value !== 0) {
			callback()
		} else if (!pattern.test(value)) {
			callback(new Error('请输入整数'));
		} else if (value < rule.min) {
			callback(new Error('不能小于' + rule.min));
		} else if (value > rule.max) {
			callback(new Error('不能大于' + rule.max));
		}
		callback();
	};
	export default {
		data() {
			return {
				form: {
					score: ""
				},
				userId: "",
				homeworkId: "",
				rules: {
					score: [{
							required: true,
							message: '请输入作业评分',
							trigger: 'change'
						},
						{
							validator: validateInteger,
							trigger: 'change'
						},
						{
							min: 1,
							max: 100,
							validator: validateIntegerLength,
							message: '值必须在 1 到 100 范围',
							trigger: 'change'
						}
					]
				}
			};
		},
		mounted() {
			if (this.rjDialogParams().data) {
				this.homeworkId = this.rjDialogParams().data.homework_id;
				this.userId = this.rjDialogParams().data.user_id;
				if (this.rjDialogParams().data.score) {
					this.form.score = this.rjDialogParams().data.score;
				}
			}
		},
		methods: {
			doCancel() {
				this.closeRjDialog();
			},
			onSubmit() {
				const loading = this.$loading({
					lock: true,
					text: '处理中...',
					spinner: 'el-icon-loading',
					background: 'rgba(0, 0, 0, 0.7)'
				});
				this.$axios.put('/v1/homeworks/' + this.homeworkId + '/users/' + this.userId + '/score?score=' + this.form.score).then(
					(response) => {
						loading.close()
						this.$message.success('作业评分成功');
						this.closeRjDialog();
					}, (response) => {
						loading.close()
						this.$message.error('作业评分失败');
					});
			}
		},
	}
</script>

<style lang="scss">
	.score-index {
		.footer-group {
			text-align: right;
		}
	}
</style>
