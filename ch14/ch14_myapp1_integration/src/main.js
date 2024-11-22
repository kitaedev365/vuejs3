import { createApp } from 'vue'
import App from './App.vue'
import { createPinia } from 'pinia'
import router from './router'
import 'bulma/css/bulma.css' // Bulma 스타일 전역 적용
// auth store 임포트 추가
import { useAuthStore } from './store/auth'

const app = createApp(App)

app.use(createPinia())
app.use(router)

// Pinia 스토어에서 상태 초기화
const authStore = useAuthStore()  // 임포트한 후 사용
authStore.initialize()

app.mount('#app')