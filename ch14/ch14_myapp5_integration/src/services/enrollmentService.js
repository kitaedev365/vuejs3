import apiClient from './api';
const enrollmentService = {
  async getAllEnrollments() {
    const response = await apiClient.get('/enrollments');
    return response.data;
  },
  async getEnrollmentByUserId(studentId, courseId) {
    const response = await apiClient.get(`/enrollments/student/${studentId}/${courseId}`);
    return response.data;
  },
  async getEnrollmentsByCourseIdAndStudentId(studentId) {
    const response = await apiClient.get(`/enrollments/student/course/${studentId}`);
    return response.data;
  },
  async getEnrollmentById(id) {
    const response = await apiClient.get(`/enrollments/${id}`);
    return response.data;
  },
  async createEnrollment(enrollmentData) {
    const response = await apiClient.post('/enrollments', enrollmentData);
    return response.data;
  },
  async updateEnrollment(id, updatedEnrollmentData) {
    const response = await apiClient.put(`/enrollments/${id}`, updatedEnrollmentData);
    return response.data;
  },
  async deleteEnrollment(id) {
    await apiClient.delete(`/enrollments/${id}`);
  },
  async getEnrollmentsByCourseId(courseId) {
    const response = await apiClient.get(`/enrollments/course/${courseId}`);
    return response.data;
  },
  async getEnrollmentsByStudentId(studentId) {
    const response = await apiClient.get(`/enrollments/student/${studentId}`);
    return response.data;
  },
};
export default enrollmentService;