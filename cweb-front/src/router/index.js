import Vue from 'vue'
import Router from 'vue-router'
import courseManage from '@components/homePage/Layout'
import articleManage from '@components/articlesList/index'
import courseDetail from '@components/course/Content'
import myStudy from '@components/study/index'
import login from '@components/login/index'
import filePlay from '@components/course/filePlay'
import signIndex from '@components/sign/index'
import homework from '@components/homework/index'
import testFrame from '@components/course/testFrame'
import userInfo from '@components/user/index.vue'
import test from '@components/front2/index.vue'
import functionIndex from '@components/front2/functionPackage.vue'
import science from '@components/front2/science.vue'
import attach from '@components/front2/attach.vue'
import question from '@components/front2/question.vue'

Vue.use(Router)

export default new Router({
	routes: [{
			path: '/',
			name: "index",
			redirect: '/index'
		},
		{
			path: '/function',
			name: "function",
			component: functionIndex,
		},
		{
			path: '/science',
			name: "science",
			component: science,
		},
		{
			path: '/attach',
			name: "attach",
			component: attach,
		},
		{
			path: '/question',
			name: "question",
			component: question,
		},
		{
			path: '/login',
			name: "login",
			component: login,
		},
		{
			path: '/test',
			name: "test",
			component: test,
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
			}, {
				path: 'filePlay',
				name: "filePlay",
				component: filePlay,
			}, {
				path: 'sign',
				name: "sign",
				component: signIndex,
			}, {
				path: 'homework',
				name: "homework",
				component: homework,
			}, {
				path: 'userInfo',
				name: "userInfo",
				component: userInfo,
			}]
		}
	]
})
