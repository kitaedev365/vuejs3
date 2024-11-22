import { ref } from 'vue';

const isLoggedIn = ref(false); // 로그인 상태를 저장하는 ref

// 상태를 변경하는 메서드
const setLoginStatus = (status) => {
  isLoggedIn.value = status;
};

// 상태를 가져오는 메서드
const getLoginStatus = () => {
  return isLoggedIn.value;
};

export { setLoginStatus, getLoginStatus };