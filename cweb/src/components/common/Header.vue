<template>
	<header>
		<nav>
			<div class="nav-title">
				课程管理后台
			</div>
			<div class="user_info">
				<img :src="user.img_url" />
				<el-dropdown trigger="click">
					<span class="el-dropdown-link">
      				  {{user.name}}
      				  <i class="el-icon-caret-bottom el-icon--right"></i>
      				</span>
					<el-dropdown-menu slot="dropdown">
						<el-dropdown-item>
							<a style="padding: 2px 30px;" @click="go2Exit" href="javascript:">退出</a>
						</el-dropdown-item>
					</el-dropdown-menu>
				</el-dropdown>
			</div>
		</nav>
		<rjDialog></rjDialog>
	</header>
</template>

<script>
	var iconImg = require("../../assets/img/user-icon.jpg");
	import logout from '@components/page/user/logout.vue'
	import rjDialog from '@components/common/dialog.vue'
	
	export default {
		components:{
			logout,
			rjDialog
		},
		name: 'Header',
		data() {
			return {
				user: {
					name: "Admin",
					icon: iconImg,
					img_url:""
				}
			}
		},
		mounted() {
			const user = JSON.parse(localStorage.getItem("USER"));
			if(user) {
				this.user.name = user.user_name;
				this.user.img_url = user.photo_path;
			}
		},
		methods: {
			go2Exit(){
				this.rjDialog.
				title("退出提示").
				width("500px").
				top("2%").
				closeOnClickModal(false).
				currentView(logout, {}).
				sizeSelf("admin-logout-index").
				showClose(true).
				then((opt) => {}).show();
			}
		}
	}
</script>

<style scoped>
	header {
		background-color: #324157;
		width: 100%;
		height: 60px;
	}

	.nav-title {
		float: left;
		width: 150px;
		height: 60px;
		color: white;
		line-height: 60px;
		font-size: 22px;
		padding: 0 0 0 10px;
	}

	.user_info {
		float: right;
		font-size: 0;
		padding-right: 10px;
	}

	.user_info img {
		width: 40px;
		height: 40px;
		border-radius: 50%;
		padding: 10px 5px;
	}

	.user_info div {
		cursor: pointer;
		float: right;
		color: white;
		font-size: 16px;
		line-height: 60px;
	}

	.el-icon-caret-bottom {
		color: #D3DCE6;
		font-size: 12px;
	}
</style>