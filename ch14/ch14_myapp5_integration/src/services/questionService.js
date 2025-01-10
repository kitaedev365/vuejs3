import apiClient from './api';
const questionService = {
  async getAllQuestions() {
    const response = await apiClient.get('/questions');
    return response.data;
  },
  async getQuestionById(id) {
    const response = await apiClient.get(`/questions/${id}`);
    return response.data;
  },
  async createQuestion(questionData) {
    const response = await apiClient.post('/questions', questionData);
    return response.data;
  },
  async updateQuestion(id, updatedQuestionData) {
    const response = await apiClient.put(`/questions/${id}`, updatedQuestionData);
    return response.data;
  },
  async deleteQuestion(id) {
    await apiClient.delete(`/questions/${id}`);
  },
  async getQuestionsByCourseId(courseId) {
    const response = await apiClient.get(`/questions/course/${courseId}`);
    return response.data;
  },
};
export default questionService;