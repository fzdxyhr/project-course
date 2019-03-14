<template>
	<div class="add-user-index">
		<el-form ref="form" :model="form" :inline="true" label-width="80px">
			<el-form-item label="用户名称">
				<el-input v-model="form.user_name"></el-input>
			</el-form-item>
			<el-form-item label="账号">
				<el-input v-model="form.account"></el-input>
			</el-form-item>
			<el-form-item label="身份证">
				<el-input v-model="form.id_card"></el-input>
			</el-form-item>
			<!-- <el-form-item label="密码">
				<el-input type="password" v-model="form.password"></el-input>
			</el-form-item> -->
			<el-form-item label="性别">
				<el-radio-group v-model="form.sex">
					<el-radio :label="1">男</el-radio>
					<el-radio :label="2">女</el-radio>
				</el-radio-group>
			</el-form-item>
			<el-form-item v-if="role =='admin'" label="角色">
				<el-select v-model="form.role" placeholder="请选择">
					<el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item label="所属班级" v-if="form.role === 'student'">
				<el-select v-model="form.class_id" placeholder="请选择">
					<el-option v-for="item in classes" :key="item.value" :label="item.label" :value="item.value">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item label="手机号码">
				<el-input v-model="form.telephone"></el-input>
			</el-form-item>
			<div class="clearfix"></div>
			<el-form-item label="用户图片">
				<el-upload name="editormd-image-file" class="avatar-uploader" :action="host+`/v1/courses/images/upload`"
				 :show-file-list="false" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
					<img v-if="showImage" :src="imageUrl" class="avatar">
					<i v-else class="el-icon-plus avatar-uploader-icon"></i>
				</el-upload>
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
					user_name: "",
					role: "",
					class_id: "",
					account: "",
					password: "",
					sex: 1,
					id_card: "",
					telephone: "",
					photo_path: ""
				},
				role: "student",
				showImage: false,
				options: [{
					label: "教师",
					value: "teacher"
				}, {
					label: "学生",
					value: "student"
				}],
				classes: [],
        rules: {
        	user_name: [{
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
        	}]
        },
        loading:""
			}
		},
		mounted() {
			const user = JSON.parse(localStorage.getItem("USER"));
			if (user) {
				this.role = user.role;
				if (this.role === 'admin') {
					this.options.unshift({
						label: "管理员",
						value: "admin"
					})
				}
			}
			if (this.rjDialogParams().data) {
				this.form = JSON.parse(JSON.stringify(this.rjDialogParams().data));
				if (this.form.photo_path) {
					this.showImage = true;
				}
			}
			this.findClasses();
		},
		computed: {
			imageUrl() {
				return this.form.photo_path;
			},
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
			handleAvatarSuccess(file) {
        this.loading.close();
				this.form.photo_path = file;
				this.showImage = true;
			},
			beforeAvatarUpload(file) {
         this.loading = this.$loading({
        	lock: true,
        	text: '上传中...',
        	spinner: 'el-icon-loading',
        	background: 'rgba(0, 0, 0, 0.7)'
        });
				const isLt2M = file.size / 1024 / 1024 < 4;
				if (!isLt2M) {
					this.$message.error('上传图片大小不能超过 4MB!');
				}
				return isLt2M;
			},
			go2Add() {
				this.form.password = this.$md5(this.form.password)
				if (this.form.id) {
					this.$axios.put("/v1/users/" + this.form.id, this.form).then((response) => {
						let message = response.data;
						this.$message.success('修改用户成功');
						this.closeRjDialog();
					}, (response) => {
						this.$message.error('修改用户失败');
					});
				} else {
					this.$axios.post("/v1/users", this.form).then((response) => {
						let message = response.data;
						this.$message.success('新增用户成功');
						this.closeRjDialog();
					}, (response) => {
						this.$message.error('新增用户失败');
					});
				}
			},
			doCancel() {
				this.closeRjDialog();
			}
		}
	}
</script>

<style lang="scss">
	.add-user-index {
		.avatar-uploader .el-upload {
			border: 1px dashed #d9d9d9;
			border-radius: 6px;
			cursor: pointer;
			position: relative;
			overflow: hidden;
		}

		.avatar-uploader .el-upload:hover {
			border-color: #409EFF;
		}

		.avatar-uploader-icon {
			font-size: 28px;
			color: #8c939d;
			width: 250px;
			height: 178px;
			line-height: 178px;
			text-align: center;
		}

		.avatar {
			width: 250px;
			height: 178px;
			display: block;
		}

		.el-select .el-input__inner {
			width: 202px;
		}
	}
</style>
