import Vue from 'vue'
import Router from 'vue-router'

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
			children: [
//         {
// 				path: '/CourseManage',
// 				component: resolve => require(['../components/page/CourseManage.vue'], resolve)
// 			},
      ]
		}
	]
})
