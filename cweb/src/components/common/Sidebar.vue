<template>
	<el-menu class="diyscrollbar" background-color="#324157" text-color="#fff" :default-active="onRoutes" @select="handleSelect"
	 unique-opened router>
		<!-- <el-submenu index="user"> -->
		<!-- <template slot="title"><i class="course-icon-user-manage"></i>用户管理</template> -->

		<!-- <el-menu-item index="PermissionsManage">权限管理</el-menu-item> -->
		<!-- </el-submenu> -->
		<el-menu-item index="index">
			<i class="course-icon-index"></i>首页
		</el-menu-item>
		<el-menu-item index="UserInfoManage">
			<i class="course-icon-user-manage"></i>用户管理
		</el-menu-item>
		<el-menu-item index="CourseManage">
			<i class="course-icon-course-manage"></i>课程管理
		</el-menu-item>
		<el-menu-item index="ClassManage">
			<i class="course-icon-class-manage"></i>班级管理
		</el-menu-item>
		<!-- <el-menu-item index="ArticlesManage">
			<i class="el-icon-document"></i>文章管理
		</el-menu-item> -->
		<el-menu-item index="TagsManage">
			<i class="course-icon-tag-manage"></i>标签管理
		</el-menu-item>
		<el-menu-item index="HomeWorkManage">
			<i class="course-icon-homework-manage"></i>作业管理
		</el-menu-item>
		<el-menu-item index="TeachManage">
			<i class="course-icon-teach-manage"></i>教学管理
		</el-menu-item>
	</el-menu>
</template>

<script>
	export default {
		name: "Sidebar",
		computed: {
			onRoutes() {
				return this.$route.path.replace('/', '');
			}
		},
		methods: {
			handleSelect(key, keyPath) {
				var key_arr = {
					index:"首页",
					user: "用户管理",
					UserInfoManage: "用户管理",
					PermissionsManage: "权限管理",
					CourseManage: "课程管理",
					ClassManage: "班级管理",
					ArticlesManage: "文章管理",
					TagsManage: "标签管理",
					HomeWorkManage: "作业管理",
					TeachManage:"教学管理"
				};
				var text_arr = [];
				for (var i = 0, l = keyPath.length; i < l; i++) {
					text_arr.push(key_arr[keyPath[i]]);
				}
				this.$emit('childSetBreadcrumb', text_arr)
			}
		}
	}
</script>

<style>
	.el-menu {
		height: 100%;
		/* background-color: white!important; */
		overflow: auto;
	}
</style>
