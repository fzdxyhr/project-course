import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

const store = new Vuex.Store({
  state: {
    host: "http://47.101.194.23:8085"
    // host: "http://localhost:8085"
  },
  mutations: {
//     increment (state) {
//       state.count++
//     }
  }
})

// 刷新页面，检测是否已经登陆，如果已经登陆开始启动刷新token定时器
// store.dispatch('tokenModule/initRefreshTokenInterval')

export default store
