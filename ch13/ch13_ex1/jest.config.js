module.exports = {
  // Vue 파일을 처리하기 위해 vue-jest 추가
  transform: {
    '^.+\\.vue$': 'vue-jest',
    '^.+\\.js$': 'babel-jest',
  },
  // 경로 별칭(@) 설정 추가
  moduleNameMapper: {
    '^@/(.*)$': '<rootDir>/src/$1',
  },
  // Jest 환경에서 필요할 수 있는 추가 설정
  testEnvironment: 'jsdom',
};