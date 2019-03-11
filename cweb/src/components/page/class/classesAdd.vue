<template>
	<div class="classes-add-index">
		<el-form ref="form" :model="form" label-width="80px" :rules="rules">
			<el-form-item label="班级名称" prop="class_name">
				<el-input v-model="form.class_name"></el-input>
			</el-form-item>
			<el-form-item label="班级描述" prop="class_desc">
				<el-input v-model="form.class_desc"></el-input>
			</el-form-item>
			<el-form-item label="教学进度" prop="progress">
				<el-input v-model="form.progress"></el-input>
			</el-form-item>
			<el-form-item label="授课老师" prop="teacher_id">
				<el-select v-model="form.teacher_id" placeholder="请选择">
					<el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item label="班级人数" prop="max_student">
				<el-input v-model="form.max_student"></el-input>
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
	export default {
		data() {
			return {
				form: {
					class_name: "",
					class_desc: "",
					teacher_id: "",
					max_student: "",
					progress: 0
				},
				options: [],
				rules: {
					class_name: [{
						required: true,
						message: '请输入班级名称',
						trigger: 'change'
					}, ],
					class_desc: [{
						required: true,
						message: '请输入班级描述',
						trigger: 'change'
					}],
					teacher_id: [{
						required: true,
						message: '请选择授课老师',
						trigger: 'change'
					}],
					max_student: [{
						required: true,
						message: '请输入班级人数',
						trigger: 'change'
					}],
					progress: [{
						required: true,
						message: '请输入教学进度',
						trigger: 'change'
					}],
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
				this.$axios.get("/v1/users?page_no=1&page_size=10000&role=teacher").then((response) => {
					let message = response.data;
					this.options = [];
					message.items.forEach((item) => {
						let temp = {};
						temp.value = item.id;
						temp.label = item.user_name;
						this.options.push(temp);
					});
				}, (response) => {
					this.$message.error('获取班级失败');
				});
			},
			go2Add() {
				this.$refs['form'].validate((valid) => {
					if (valid) {
						if (this.form.id) {
							this.$axios.put("/v1/classes/" + this.form.id, this.form).then((response) => {
								let message = response.data;
								this.$message.success('修改班级成功');
								this.closeRjDialog();
							}, (response) => {
								this.$message.error('修改班级失败');
							});
						} else {
							this.$axios.post("/v1/classes", this.form).then((response) => {
								let message = response.data;
								this.$message.success('添加班级成功');
								this.closeRjDialog();
							}, (response) => {
								this.$message.error('添加班级失败');
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
	.classes-add-index {}
</style>
