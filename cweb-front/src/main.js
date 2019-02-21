import Vue from 'vue'
import App from './App'
import router from './router'
import vueResource from 'vue-resource'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-default/index.css'
import './assets/css/reset1.2.css'
import md5 from 'js-md5'

Vue.config.productionTip = false

Vue.prototype.$md5 = md5

Vue.use(ElementUI);
Vue.use(vueResource);
new Vue({
	el: '#app',
	router,
	template: '<App/>',
	components: {
		App
	}
});
