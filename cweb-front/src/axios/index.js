/*引入Vue框架*/
import Vue from 'vue';
/*引入资源请求插件*/
import axios from 'axios';


// 配置默认的host,假如你的API host是：http://api.htmlx.club
axios.defaults.baseURL = 'http://47.101.194.23:8085';
// axios.defaults.baseURL = 'http://localhost:8085';
axios.defaults.headers.post['Accept'] = 'application/json';
axios.defaults.headers.post['Content-Type'] = 'application/json';  

// 添加请求拦截器
axios.interceptors.request.use(function (config) {
　　// 在发送请求之前做些什么
	// 添加鉴权头部
//   	if (!config.headers.Authorization) {
//     	const token = store.getters['tokenModule/token']
//     	console.log('token',token)
// 	    if (token && token.access_token) {
// 	      	config.headers.Authorization = `Bearer ${token.access_token}`
// 	    }
// 	}
	if (!config.headers.USER_ID) {
    	const userInfo = JSON.parse(localStorage.getItem('WEBFRONT_USER'));
	    if (userInfo && userInfo.id) {
	      	config.headers.USER_ID = userInfo.id;
	    }
	}
　　return config
}, function (error) {
　　// 对请求错误做些什么
	return Promise.reject(error)
});

// 添加响应拦截器
axios.interceptors.response.use(function (response) {
　　// 对响应数据做点什么
　　return response
}, function (error) {
　　// 对响应错误做点什么
　　return Promise.reject(error)
});

/*使用axios插件*/
Vue.prototype.$axios = axios;