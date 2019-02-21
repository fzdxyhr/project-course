<template>
	<header>
		<nav>
			<el-menu class="menu-ul" theme="dark" :default-active="activeIndex" menu-trigger="hover" mode="horizontal">
				<li class="nav-logo">
					<a href="javascript:" @click="go2Page('courseManage')">iCollege</a>
				</li>
				<el-menu-item index="1" @click="go2Page('courseManage')">
					主页
				</el-menu-item>
				<el-menu-item index="2" @click="go2Page('articleManage')">
					文章列表
				</el-menu-item>
				<!-- <li class="nav-search">
					<el-input placeholder="搜索文章" icon="search" v-model="search" :on-icon-click="handleIconClick">
					</el-input>
				</li> -->
				<li class="nav-user">
					<el-dropdown>
						<span class="el-dropdown-link">
							<a href="#" class="user-icon">
								<img src="../../../static/img/carousel.jpg" />
							</a>
						</span>
						<el-dropdown-menu slot="dropdown">
							<el-dropdown-item>
								<a href="#">
									<i class="fa fa-user fa-fw"></i> 我的主页
								</a>
							</el-dropdown-item>
							<el-dropdown-item>
								<i class="fa fa-sign-out fa-fw"></i> 退出登录
							</el-dropdown-item>
						</el-dropdown-menu>
					</el-dropdown>
				</li>
				<li class="not-login">
					<a href="#">未登录</a>
				</li>
			</el-menu>
		</nav>
	</header>
</template>

<script>
	export default {
		components: {},
		name: 'nav',
		data: function() {
			return {
				search: '',
				activeIndex:"1"
			};
		},
		methods: {
			handleSelect(key, keyPath) {
				console.log(key, keyPath);
			},
			handleIconClick(ev) {
				console.log(ev);
			},
			go2Page(url) {
				this.$router.push({
					name: url
				});
			}
		}
	}
</script>

<style>
	nav {
		background-color: #324157;
	}

	.menu-ul {
		max-width: 1200px;
		margin: 0 auto !important;
	}

	.nav-logo {
		float: left;
		width: 90px;
		height: 60px;
	}

	.nav-logo a:visited,
	.nav-logo a:link {
		color: #FFFFFF;
		line-height: 60px;
		font-size: 22px;
	}

	/*.nav-logo img {
		max-width: 60px;
		max-height: 40px;
		padding: 10px 15px;
	}*/

	.nav-search {
		float: left;
		/*line-height: 60px;*/
		/*ie9下不行*/
		padding: 12px 10px;
	}

	.nav-search .el-input__inner {
		border-radius: 40px;
		outline: none;
	}

	.nav-user {
		float: right;
	}

	.nav-user a.user-icon {}

	.nav-user .user-icon img {
		margin: 10px 0 0px;
		width: 40px;
		height: 40px;
		border-radius: 50%;
	}

	.published {
		float: right;
		line-height: 60px;
		padding: 0 0px 0 15px;
	}

	.not-login {
		float: right;
	}

	.not-login a {
		color: #bfcbd9;
		line-height: 60px;
		padding: 0 5px;
		font-size: 13px;
	}
</style>
