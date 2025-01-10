import apiClient from './api';
const reservationService = {
  async getAllReservations() {
    const response = await apiClient.get('/reservations');
    return response.data;
  },
  async getReservationById(id) {
    const response = await apiClient.get(`/reservations/${id}`);
    return response.data;
  },
  async createReservation(reservationData) {
    const response = await apiClient.post('/reservations', reservationData);
    return response.data;
  },
  async getReaervationsByCourseIdAndStudentId(userId) {
    const response = await apiClient.get(`/reservations/student/course/${userId}`);
    return response.data;
  },
  async updateReservation(id, updatedReservationData) {
    const response = await apiClient.put(`/reservations/${id}`, updatedReservationData);
    return response.data;
  },
  async deleteReservation(id) {
    await apiClient.delete(`/reservations/${id}`);
  },
  async getReservationsByCourseId(courseId) {
    const response = await apiClient.get(`/reservations/course/${courseId}`);
    return response.data;
  },
  async getReservationsByStudentId(studentId) {
    const response = await apiClient.get(`/reservations/student/${studentId}`);
    return response.data;
  },
};
export default reservationService;