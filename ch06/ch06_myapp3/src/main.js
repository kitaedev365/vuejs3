import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
//import './styles/app.scss' // Foundation CSS 포함
createApp(App)
  .use(store)
  .use(router)
  .mount('#app')