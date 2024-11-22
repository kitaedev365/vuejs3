module.exports = {
    env: {
      browser: true,
      es2021: true,
    },
    extends: [
      'eslint:recommended',
      'plugin:vue/vue3-recommended', // Vue 3 Composition API에 필요한 규칙 추가
    ],
    parserOptions: {
      ecmaVersion: 12,
      sourceType: 'module',
    },
    plugins: [
      'vue',
    ],
    rules: {
      'vue/no-setup-props-destructure': 'off', // props 구조분해 관련 경고 해제
    },
  }