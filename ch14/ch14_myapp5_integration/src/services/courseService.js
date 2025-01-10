import apiClient from './api';
import axios from 'axios';
const courseService = {
  async getAllCourses() {
    const response = await apiClient.get('/courses');
    return response.data;
  },
  async getLastCourse() {
    const response = await apiClient.get(`/courses/last`);
    return response.data;
  },
  async getCourseById(id) {
    const response = await apiClient.get(`/courses/${id}`);
    return response.data;
  },
  async getCourseInfoListById(courseId) {
    const response = await apiClient.get(`/course-info/course/${courseId}`);
    return response.data;
  },
  async createCourse(courseData) {
    const response = await apiClient.post('/courses/new', courseData);
    return response.data;
  },
  async createCourseInfo(courseInfo) {
    const response = await apiClient.post('/course-info/upload', courseInfo, {
      headers: {
        'Content-Type': 'multipart/form-data',
      }});
    return response.data;
  },
  async updateCourse(id, updatedCourseData) {
    const response = await apiClient.put(`/courses/${id}`, updatedCourseData);
    return response.data;
  },
  async updateCourseInfo(courseId, updatedCourseInfo) {
    const response = await apiClient.put(`/course-info/course/${courseId}`, updatedCourseInfo, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    });
    return response.data;
  },
  async deleteCourse(id) {
    await apiClient.delete(`/courses/${id}`);
  },
};
export default courseService;