export const saveAuthToken = (token) => {  // 토큰 저장 함수
    localStorage.setItem('authToken', token);
};
export const removeAuthToken = () => {  // 토큰 삭제 함수
    localStorage.removeItem('authToken');
};
export const getAuthToken = () => {  // 토큰 가져오기 함수
    return localStorage.getItem('authToken');
};
export const isAuthenticated = () => {  // 사용자 인증 상태
    const token = getAuthToken();
    return token !== null;
};  