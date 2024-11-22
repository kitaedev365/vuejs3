import { defineConfig } from 'cypress';
export default defineConfig({
  e2e: {
    supportFile: false,
    setupNodeEvents(on, config) {
    },
    baseUrl: 'http://localhost:5173', // Vite의 기본 포트 설정
  },
})