import apiClient from './api';
const userService = {
  async register(userData) {
    const response = await apiClient.post('/users/register', userData);
    return response.data;
  },
  async getUser(username){
    try {
      const userResponse = await apiClient.get(`/users/${username}`); // 사용자 정보 가져오기
      return userResponse.data;
    } catch (error) {
      throw error; // 에러 발생 시 상위 호출자에게 전달
    }
  },
  async getUserProfile(id) {
    const response = await apiClient.get(`/users/${id}`);
    return response.data;
  },
  async checkUsername(username) {
    const response = await apiClient.get(`/users/check-username`, {
      params: { username },
    });
    return response.data;
  },
  async checkEmail(email) {
    const response = await apiClient.get(`/users/check-email`, {
      params: { email },
    });
    return response.data;
  },
  async updateUser(id, updatedUserData) {
    const response = await apiClient.put(`/users/${id}`, updatedUserData);
    return response.data;
  },
  async deleteUser(id) {
    await apiClient.delete(`/users/${id}`);
  },
};
export default userService;