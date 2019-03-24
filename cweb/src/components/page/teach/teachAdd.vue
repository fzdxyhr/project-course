<template>
	<div class="classes-add-index">
		<el-form ref="form" :model="form" label-width="120px" :rules="rules" :inline="true">
			<el-form-item label="教学班级" prop="teach_classes">
				<el-select v-model="form.teach_classes" placeholder="请选择">
					<el-option v-for="item in classes" :key="item.value" :label="item.label" :value="item.value">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item label="教学内容" prop="teach_content">
				<!-- <el-input v-model="form.teach_content"></el-input> -->
				<el-cascader :options="options" v-model="form.teach_content">
				</el-cascader>
			</el-form-item>
			<el-form-item label="教学评价" prop="teach_evaluation">
				<el-rate style="padding-top: 10px;" v-model="form.teach_evaluation" show-text :texts="texts">
				</el-rate>
			</el-form-item>
			<div class="clearfix"></div>
			<el-form-item label="教学说明" prop="teach_remark">
				<el-input style="width: 573px;" type="textarea" :rows="5" v-model="form.teach_remark"></el-input>
			</el-form-item>
			<div class="clearfix"></div>
			<el-form-item label="教学开始时间" prop="teach_start_time">
				<el-date-picker v-model="form.teach_start_time" value-format="yyyy-MM-dd HH:mm:ss" type="datetime" placeholder="选择教学时间">
				</el-date-picker>
			</el-form-item>
			<el-form-item label="教学结束时间" prop="teach_end_time">
				<el-date-picker v-model="form.teach_end_time" value-format="yyyy-MM-dd HH:mm:ss" type="datetime" placeholder="选择教学时间">
				</el-date-picker>
			</el-form-item>
		</el-form>
		<div style="text-align: right;">
			<el-button v-if="!form.id" type="primary" @click="go2Add">新增</el-button>
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
					teach_classes: "",
					teach_content: [],
					teach_evaluation: "",
					teach_remark: "",
					teach_start_time: "",
					teach_end_time: ""
				},
				texts: ["很差", "差", "中等", "良好", "优秀"],
				classes: [],
				options: [],
				rules: {
					teach_classes: [{
						required: true,
						message: '请输入教学班级',
						trigger: 'change'
					}, ],
					teach_content: [{
						required: true,
						message: '请输入教学内容',
						trigger: 'change'
					}],
					teach_evaluation: [{
						required: true,
						message: '请选择教学评价',
						trigger: 'change'
					}],
					teach_remark: [{
						required: true,
						message: '请输入教学说明',
						trigger: 'change'
					}],
					teach_start_time: [{
						required: true,
						message: '请输入教学开始时间',
						trigger: 'change'
					}],
					teach_end_time: [{
						required: true,
						message: '请输入教学结束时间',
						trigger: 'change'
					}],
				}
			};
		},
		mounted() {
			this.findClasses();
			this.findCourses();
			if (this.rjDialogParams().data) {
				this.form = JSON.parse(JSON.stringify(this.rjDialogParams().data));
				if (this.form.teach_content) {
					let temp = this.form.teach_content.split(",");
					console.log(temp)
					this.form.teach_content = [];
					if(temp && temp.length >0){
						temp.forEach((item)=>{
							this.form.teach_content.push(parseInt(item));
						})
					}
				}
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
			findCourses() {
				this.$axios.get("/v1/courses/chapter/tree").then((response) => {
					this.options = response.data;
				}, (response) => {
					this.$message.error('获取课程树失败');
				});
			},
			go2Add() {
				this.$refs['form'].validate((valid) => {
					if (valid) {
						let req = {
							...this.form
						}
						if (req.teach_content && req.teach_content.length > 0) {
							req.teach_content = req.teach_content.join(",")
						}
						if (this.form.id) {
							this.$axios.put("/v1/teachs/" + this.form.id, req).then((response) => {
								let message = response.data;
								this.$message.success('修改教学信息成功');
								this.closeRjDialog();
							}, (response) => {
								this.$message.error('修改教学信息失败');
							});
						} else {
							this.$axios.post("/v1/teachs", req).then((response) => {
								let message = response.data;
								this.$message.success('添加教学信息成功');
								this.closeRjDialog();
							}, (response) => {
								this.$message.error('添加教学信息失败');
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
