import { defineConfig } from '@playwright/test';
export default defineConfig({
  testDir: './e2e/tests',
  use: {
    baseURL: 'http://localhost:5173',  // Vite 개발 서버 주소
    headless: true,  // true로 설정하면 브라우저가 보이지 않게 실행
    screenshot: 'only-on-failure',
    video: 'retain-on-failure',
  },
  projects: [
    { name: 'Chromium', use: { browserName: 'chromium' } },
    { name: 'Firefox', use: { browserName: 'firefox' } },
    { name: 'WebKit', use: { browserName: 'webkit' } },
  ],
});