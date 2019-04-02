<template>
	<div class="index-content">
		<div class="top-content">
			<i class="el-icon-share"></i>个人中心
		</div>
		<el-tabs type="border-card" v-model="activeName">
			<el-tab-pane name="person">
				<span slot="label"><i class="el-icon-date"></i> 个人信息</span>
				<div class="body-content">
					<el-row>
						<el-col :span="10">
							<div class="photo-info">
								<img :src="form.photo_path" width="200" height="220" />
								<div class="user-name">{{form.user_name}}</div>
								<fileUpload style="width: 110px;margin-left: 218px;margin-top: 15px;" @change="fileChange" buttonText="修改头像"
								 :showFileList="false" :limit="2"></fileUpload>
							</div>
						</el-col>
						<el-col :span="14">
							<div class="detail-info">
								<el-form ref="form" :model="form" label-width="120px" :rules="basicRules">
									<el-form-item label="所属班级">
										<label>{{form.class_name}}</label>
									</el-form-item>
									<el-form-item label="性别" style="width: 85%;">
										<el-radio v-model="form.sex" :label="1">男</el-radio>
										<el-radio v-model="form.sex" :label="2">女</el-radio>
									</el-form-item>
									<el-form-item label="身份证" style="width: 85%;" prop="id_card">
										<el-input v-model="form.id_card"></el-input>
									</el-form-item>
									<el-form-item label="手机号码" style="width: 85%;" prop="telephone">
										<el-input v-model="form.telephone" suffix-icon="el-icon-mobile-phone"></el-input>
									</el-form-item>
								</el-form>
							</div>
						</el-col>
					</el-row>
					<div class="footer">
						<el-button type="primary" icon="el-icon-check" style="margin-top: 25px;margin-bottom: 10%;" @click="updateUser">保存</el-button>
					</div>
				</div>
			</el-tab-pane>
			<el-tab-pane label="修改密码" name="password">
				<span slot="label"><i class="el-icon-edit-outline"></i> 修改密码</span>
				<div class="update-password">
					<el-form ref="pwdForm" :model="pwdForm" label-width="120px" :rules="rules">
						<el-form-item label="旧密码" style="width: 75%;" prop="old_password">
							<el-input type="password" v-model="pwdForm.old_password"></el-input>
						</el-form-item>
						<el-form-item label="新密码" style="width: 75%;" prop="new_password">
							<el-input type="password" v-model="pwdForm.new_password"></el-input>
						</el-form-item>
						<el-form-item label="确认新密码" style="width: 75%;" prop="repeat_new_password">
							<el-input type="password" v-model="pwdForm.repeat_new_password"></el-input>
						</el-form-item>
					</el-form>
				</div>
				<div class="footer">
					<el-button type="primary" icon="el-icon-check" style="margin-top: 25px;margin-bottom: 10%;" @click="updateUserPwd">保存</el-button>
				</div>
			</el-tab-pane>
		</el-tabs>
	</div>
</template>

<script>
	import fileUpload from '@components/common/file-upload.vue';

	var validatePhone = (rule, value, callback) => {
		var pattern = new RegExp("^(1)(3|4|5|6|7|8|9)[0-9]{9}$");
		if (value && !pattern.test(value)) {
			callback(new Error('请输入正确的手机号码'));
		}
		callback();
	};
	var validateIdCard = (rule, value, callback) => {
        var pattern = new RegExp("^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$");
        if (value && !pattern.test(value)) {
            callback(new Error('请输入正确的身份证号码'));
        }
        callback();
    };

	export default {
		components: {
			fileUpload
		},
		data() {
			return {
				// activeName:"person",
				form: {
					photo_path: "",
					sex: 1,
					id_card: "",
					telephone: ""
				},
				pwdForm: {
					old_password: "",
					new_password: "",
					repeat_new_password: ""
				},
				rules: {
					old_password: [{
						required: true,
						message: '请输入原密码',
						trigger: 'change'
					}, ],
					new_password: [{
						required: true,
						message: '请输入新密码',
						trigger: 'change'
					}],
					repeat_new_password: [{
						required: true,
						message: '请输入确认新密码',
						trigger: 'change'
					}]
				},
				basicRules:{
					telephone: [{
						validator: validatePhone,
						trigger: 'change'
					}],
					id_card: [{
						validator: validateIdCard,
						trigger: 'change'
					}]
				},
			}
		},
		methods: {
			findUser() {
				let self = this;
				let user = JSON.parse(localStorage.getItem("WEBFRONT_USER"));
				this.$axios
					.get("/v1/users/" + user.id)
					.then((response) => {
						self.form = response.data;
					}).catch((error) => {
						console.log(error);
					});
			},
			updateUser() {
				let self = this;
				const user = JSON.parse(localStorage.getItem("WEBFRONT_USER"));
				this.$axios
					.put("/v1/users/" + user.id, self.form)
					.then((response) => {
						self.form = response.data;
						self.$message.success("保存成功");
						self.findUser();
					}).catch((error) => {
						console.log(error);
					});
			},
			updateUserPwd() {
				let self = this;
				this.$refs['pwdForm'].validate((valid) => {
					if (valid) {
						if (self.pwdForm.new_password != self.pwdForm.repeat_new_password) {
							self.$message.error("确认密码与新密码需一致");
							return;
						}
						const user = JSON.parse(localStorage.getItem("WEBFRONT_USER"));
						let req = {};
						req.old_password = self.$md5(self.pwdForm.old_password);
						req.new_password = self.$md5(self.pwdForm.new_password);
						this.$axios
							.put("/v1/users/" + user.id + "/pwd", req)
							.then((response) => {
								self.form = response.data;
								self.$message.success("修改密码成功,重新登录后生效");
							}).catch((error) => {
								self.$message.error(error.response.data.message || "修改密码失败");
							});
					} else {
						console.log('error submit!!');
						return false;
					}
				});
			},
			fileChange(res) {
				if (res) {
					this.form.photo_path = res;
				}
			}

		},
		mounted() {
			// this.activeName = this.$route.params.active_name;
			this.findUser();
		},
		computed: {
			activeName() {
				return this.$route.params.active_name || "person"
			}
		},
	}
</script>

<style>
	.top-content {
		position: absolute;
		z-index: 1;
		right: 20px;
		font-size: 19px;
		line-height: 37px;
	}

	.photo-info {
		text-align: center;
		margin-top: 5%;
	}

	.user-name {
		margin-top: 20px;
	}

	.sex-button {
		margin-top: 15px;
	}

	.detail-info {
		margin-top: 2%;
	}

	.update-password {
		margin-left: 15%;
	}

	.footer {
		text-align: center;
	}
</style>
