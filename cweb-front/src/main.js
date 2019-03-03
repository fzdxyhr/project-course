import Vue from 'vue';
import App from './App';
import router from './router';
import vueResource from 'vue-resource';
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import './assets/css/reset1.2.css';
import md5 from 'js-md5';
import axios from './axios'; //通过import引入

Vue.prototype.$md5 = md5;

Vue.use(ElementUI);
Vue.use(vueResource);

router.afterEach((to, from, next) => {
	window.scrollTo(0, 0);
});

new Vue({
	el: '#app',
	router,
	axios,
	template: '<App/>',
	components: {
		App
	}
});
