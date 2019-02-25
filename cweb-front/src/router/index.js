import Vue from 'vue'
import Router from 'vue-router'
import courseManage from '@components/homePage/Layout'
import articleManage from '@components/articlesList/index'
import courseDetail from '@components/course/Content'
import myStudy from '@components/study/index'
import login from '@components/login/index'

Vue.use(Router)

export default new Router({
	routes: [{
			path: '/',
			redirect: '/index'
		},
		{
			path: '/login',
			name: "login",
			component: login,
		},
		{
			path: '/index',
			component: () => import('../index.vue'),
			children: [{
				path: '',
				redirect: 'courseManage'
			}, {
				path: 'courseManage',
				name: "courseManage",
				component: courseManage,
			}, {
				path: 'articleManage',
				name: "articleManage",
				component: articleManage,
			}, {
				path: 'courseDetail',
				name: "courseDetail",
				component: courseDetail,
			}, {
				path: 'myStudy',
				name: "myStudy",
				component: myStudy,
			}]
		}
	]
})
