module.exports = {
  env: {
    browser: true,
    es2021: true,
  },
  extends: [
    "plugin:vue/vue3-essential",
    "eslint:recommended",
  ],
  parserOptions: {
    ecmaVersion: 2021,
    sourceType: "module",
  },
  rules: {
    'no-undef': 'off',  // `defineProps` 관련 오류 무시
    'vue/no-setup-props-destructure': 'off' // props 구조 분해 경고 비활성화
  }
};