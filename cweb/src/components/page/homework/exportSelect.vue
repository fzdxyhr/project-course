<template>
	<div class="score-index">
		<el-form ref="form" :model="form" label-width="120px">
			<el-form-item label="请选择导出班级" prop="score">
				<el-select v-model="classId" placeholder="请选择" clearable>
					<el-option v-for="item in classes" :key="item.value" :label="item.label" :value="item.value">
					</el-option>
				</el-select>
			</el-form-item>
		</el-form>
		<div class="footer-group">
			<el-button type="primary" @click="onSubmit">导出</el-button>
			<el-button @click="doCancel">取消</el-button>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				form: {
					score: ""
				},
				userId: "",
				homeworkId: "",
				classId: "",
				classes: []
			};
		},
		mounted() {
			this.findClasses();
			if (this.rjDialogParams().data) {
				this.homeworkId = this.rjDialogParams().data.id;
			}
		},
		computed: {
			host() {
				return this.$store.state.host
			}
		},
		methods: {
			findClasses() {
				this.$axios.get("/v1/classes?page_no=1&page_size=10000").then((response) => {
					this.classes = [];
					response.data.items.forEach((item) => {
						let temp = {};
						temp.label = item.class_name;
						temp.value = item.id;
						this.classes.push(temp);
					})
				}, (response) => {
					this.$message.error('获取班级失败');
				});
			},
			doCancel() {
				this.closeRjDialog();
			},
			onSubmit() {
				window.location = this.host + "/v1/homeworks/" + this.homeworkId + "/users/export?class_id=" + this.classId;
				this.closeRjDialog();
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
