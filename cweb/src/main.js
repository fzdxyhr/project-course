import Vue from 'vue'
import App from './App'
import router from './router'
// import vueResource from 'vue-resource'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './assets/css/reset1.2.css'
import './assets/css/pagePublic.css'
import './assets/css/vve-components-icon-font.scss'
import md5 from 'js-md5'
import axios from './axios'; //通过import引入
import store from './store'; //通过import引入

import echarts from 'echarts'
Vue.prototype.$echarts = echarts 
import ECharts from 'vue-echarts/components/ECharts'  
Vue.component('v-chart', ECharts)

Vue.config.productionTip = false

Vue.prototype.$md5 = md5

Vue.use(ElementUI);
// Vue.use(vueResource);
new Vue({
	router,
	axios,
  store,
	render: h => h(App)
}).$mount('#app');
