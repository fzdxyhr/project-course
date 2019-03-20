<template>
	<div class="login-wrapper">
		<div class="login-form">
			<p class="title">登陆</p>
			<el-form label-position="right" :model="loginForm" :rules="rules" ref="loginForm">
				<el-form-item prop="account">
					<el-input prefix-icon="el-icon-fa-envelope" placeholder="请输入账号" type="text" v-model="loginForm.account"></el-input>
				</el-form-item>
				<el-form-item prop="password">
					<el-input prefix-icon="el-icon-fa-lock" placeholder="请输入密码" type="password" v-model="loginForm.password" @keyup.enter.native="handleLoginBtnClick"></el-input>
				</el-form-item>
			</el-form>
			<div class="fogot-pwd-btn">
				<router-link :to="{ name: 'resetpwd' }">忘记密码?</router-link>
			</div>
			<el-button class="login-btn" @click="handleLoginBtnClick">登录</el-button>
			<!-- <div class="line-separate"><span>或</span></div>
			<router-link :to="{ name: 'register' }">
				<el-button class="register-btn">还没有账号，去注册</el-button>
			</router-link> -->
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				loginForm: {
					account: 'admin',
					password: 'admin',
				},
				rules: {
					account: [{
							required: true,
							message: '请输入账号',
							trigger: 'blur'
						},
						{
							min: 3,
							max: 45,
							message: '长度在 3 到 45 个字符',
							trigger: 'blur'
						}
					],
					password: [{
						required: true,
						message: '请输入密码',
						trigger: 'blur'
					}, ]
				}
			}
		},
		created() {},
		methods: {
			handleLoginBtnClick() {
				this.$refs['loginForm'].validate(valid => {
					if (valid) {
						this.loginForm.password = this.$md5(this.loginForm.password);
						this.$axios.post("/v1/login", this.loginForm).then((response) => {
							let message = response.data;
							localStorage.setItem("WEBFRONT_USER", JSON.stringify(message));
							this.$router.push({
								name: "courseManage"
							});
						}, (response) => {
							this.$message.error(response.response.data.message || '账号或密码错误');
						});
					} else {
						return false;
					}
				})
			}
		}
	}
</script>

<style lang="scss">
	.login-wrapper {
		background-color: #2c2c32;
		height: 100%;

		.title {
			font-size: 30px;
			font-weight: 100;
			text-align: center;
			margin-bottom: 20px;
			color: white;
		}

		.login-form {
			position: absolute;
			left: 50%;
			top: 50%;
			transform: translate(-50%, -50%);
			width: 320px;

			.el-input {
				font-size: 16px;

				.el-input__inner {
					background: rgba(37, 33, 33, .6);
					height: 50px;
					line-height: 50px;
					border: 1px solid #808080;
					color: white;
					border-radius: 5px;
				}

				.el-input__inner:focus {
					outline: none;
					border-color: #409eff;
				}
			}

			.el-button {
				width: 100%;
				height: 50px;
				font-size: 16px;
				border-radius: 5px;

				&:focus,
				&:hover {
					border-color: #66b1ff;
				}
			}

			.login-btn {
				background-color: #c73e3e;
				color: white;
				border-color: #c73e3e;

				&:focus,
				&:hover {
					background-color: #e24444;
					border-color: #e24444;
				}
			}

			.fogot-pwd-btn {
				text-align: center;
				margin-bottom: 20px;

				a {
					color: white;
					font-size: 14px;
					text-decoration: underline;
				}
			}

			.line-separate {
				text-align: center;
				display: flex;
				align-items: center;
				padding: 20px 0;
				opacity: 0.7;

				span {
					padding: 0 20px;
					color: #eee;

				}

				&:before,
				&:after {
					display: table;
					content: ' ';
					flex: 1;
					border-top: 1px solid #808080;
				}
			}

			.register-btn {
				color: #eee;
				border: 1px solid #808080;
				background: transparent;
			}
		}
	}
</style>
