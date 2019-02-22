import Vue from 'vue'
import Router from 'vue-router'
import courseManage from '@components/homePage/Layout'
import articleManage from '@components/articlesList/index'
import courseDetail from '@components/course/Content'

Vue.use(Router)

export default new Router({
	routes: [{
			path: '/',
			redirect: '/index'
		},
		// 		{
		// 			path: '/Login',
		// 			component: resolve => require(['@components/page/Login.vue'], resolve)
		// 		},
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
			}]
		}
	]
})
