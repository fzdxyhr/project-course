import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
	routes: [{
		path: '/',
		redirect: '/login'
	}, {
		path: '/login',
		name: "login",
		component: resolve => require(['../components/page/Login.vue'], resolve)
	}, {
		path: '/Content',
		component: resolve => require(['../components/common/Content.vue'], resolve),
		children: [{
			path: '/',
			redirect: '/index'
		}, {
			path: '/CourseManage',
			component: resolve => require(['../components/page/CourseManage.vue'], resolve)
		}, {
			path: '/UserInfoManage',
			component: resolve => require(['../components/page/UserInfoManage.vue'], resolve)
		}, {
			path: '/ClassManage',
			component: resolve => require(['../components/page/class/index.vue'], resolve)
		}, {
			path: '/PermissionsManage',
			component: resolve => require(['../components/page/PermissionsManage.vue'], resolve)
		}, {
			path: '/ArticlesManage',
			component: resolve => require(['../components/page/ArticlesManage.vue'], resolve)
		}, {
			path: '/TagsManage',
			component: resolve => require(['../components/page/TagsManage.vue'], resolve)
		}, {
			path: '/HomeWorkManage',
			component: resolve => require(['../components/page/homework/index.vue'], resolve)
		}, {
			path: '/TeachManage',
			component: resolve => require(['../components/page/teach/index.vue'], resolve)
		}, {
			path: '/index',
			component: resolve => require(['../components/page/statistics/index.vue'], resolve)
		}]
	}]
})
