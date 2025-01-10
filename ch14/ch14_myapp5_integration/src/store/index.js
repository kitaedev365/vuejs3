import axios from 'axios'; // Axios 인스턴스 가져오기
import { defineStore } from 'pinia';
import { ref, watchEffect } from 'vue';
import authService from '../services/authService';
import courseService from '../services/courseService';
import enrollmentService from '../services/enrollmentService';
import questionService from '../services/questionService';
import reservationService from '../services/reservationService';
import userService from '../services/userService';
import { useRouter } from 'vue-router';

const router = useRouter();

// 사용자 인증 관련 store
export const useAuthStore = defineStore('auth', () => {
  const user = ref(localStorage.getItem('user') || '');
  const authToken = ref(localStorage.getItem('authToken') || ''); // 초기 상태를 localStorage로부터 가져오기
    // 로그인 시 호출되는 함수
  async function login(credentials) {
    try {
      const response = await authService.login(credentials);
      authToken.value = response.token;
      localStorage.setItem('authToken', response.token); // LocalStorage에 저장
      const res = await authService.getUser(credentials);
      user.value = res;
      localStorage.setItem('user', res); // LocalStorage에 저장
      localStorage.setItem('username', res.username);
      localStorage.setItem('role', res.role);
      router.push("/");
    } catch (error) {
      throw new Error('Login failed: ' + (error.response?.data?.message || error.message));
    }
  }

  // 로그아웃 시 호출되는 함수
  function logout() {
    authToken.value = '';
    user.value = null;
    localStorage.removeItem('authToken'); // LocalStorage에서 토큰 삭제
    localStorage.removeItem('user');
    localStorage.removeItem('username');
    localStorage.removeItem('role');
  }

  watchEffect(() => {
    if (authToken.value) {
      // authToken이 존재할 경우 Axios 기본 헤더에 Authorization 추가
      axios.defaults.headers.common['Authorization'] = `Bearer ${authToken.value}`;
    } else {
      // authToken이 없으면 Authorization 헤더 제거
      delete axios.defaults.headers.common['Authorization'];
    }
  });

  return {
    user,
    authToken,
    login,
    logout,
  };
});

// 강의 관련 store
export const useCoursesStore = defineStore('courses', () => {
  const courses = ref([]);
  const course = ref({});
  const courseInfoList = ref([]);
  const cour = ref({});

  async function fetchCourses() {
    try {
      const response = await courseService.getAllCourses();
      courses.value = response; // 반환된 데이터를 courses에 저장
    } catch (error) {
      console.error('Error fetching courses:', error);
    }
  }
  
  async function fetchCourse(id) {
    course.value = await courseService.getCourseById(id);
  }

  async function fetchCourseInfoListById(courseId){
    courseInfoList.value = await courseService.getCourseInfoListById(courseId);
  }

  async function fetchLastCourse() {
    cour.value = await courseService.getLastCourse();
  }

  return {
    course,
    fetchCourse,
    courses,
    fetchCourses,
    courseInfoList,
    fetchCourseInfoListById,
    cour,
    fetchLastCourse,
  };
});

// 사용자 관련 store (추가)
export const useUserStore = defineStore('user', () => {
  const user = ref({});
  const users = ref([]);

  async function fetchUsers() {
    users.value = await userService.getAllUsers();
  }

  async function fetchUser(username) {
    user.value = await userService.getUser(username);
  }

  return {
    user,
    fetchUser,
    users,
    fetchUsers,
  };
});

export const useEnrollmentStore = defineStore('enrollment', () => {
  const enrollments = ref([]);
  const enrollment = ref({});
  const enroll = ref({});
  const enrolls = ref([]);
  const isEnroll = ref(false);

  async function fetchEnrollments() {
    enrollments.value = await enrollmentService.getAllEnrollments();
  }

  async function fetchEnrollmentById(id) {
    enrollment.value = await enrollmentService.getEnrollmentById(id);
  }

  async function getEnrollmentByUserId(studentId, courseId){
    try {
      enroll.value = await enrollmentService.getEnrollmentByUserId(studentId, courseId);
      isEnroll.value = enroll.value.id >= 1;  // `true` 설정
    } catch (error) {
      console.error('Enrollment fetch error:', error);
      isEnroll.value = false;  // 오류 발생 시 `false` 설정
    }
  }

  async function getEnrollmentsByCourseIdAndStudentId(studentId) {
    enrolls.value = await enrollmentService.getEnrollmentsByCourseIdAndStudentId(studentId);
  }

  return {
    enrollments,
    fetchEnrollments,
    enrollment,
    fetchEnrollmentById,
    enroll,
    getEnrollmentByUserId,
    enrolls,
    getEnrollmentsByCourseIdAndStudentId,
    isEnroll
  };
});

export const useQuestionStore = defineStore('question', () => {
  const questions = ref([]);
  const question = ref(null);

  async function fetchQuestions() {
    questions.value = questionService.getAllQuestions();
  }

  async function fetchQuestion(id) {
    question.value = questionService.getQuestionById(id);
  }

  return {
    questions,
    fetchQuestions,
    question,
    fetchQuestion,
  };
});

export const useReservationStore = defineStore('reservation', () => {
  const reservations = ref([]);
  const reservation = ref(null);
  const res = ref([]);

  async function fetchReservations() {
    reservations.value = await reservationService.getAllReservations();
  }

  async function fetchReservation(id) {
    reservation.value = await reservationService.getReservationById(id);
  }

  async function getReaervationsByCourseIdAndStudentId(userId){
    res.value = await reservationService.getReaervationsByCourseIdAndStudentId(userId)
  }

  return {
    reservations,
    fetchReservations,
    reservation,
    fetchReservation,
    res,
    getReaervationsByCourseIdAndStudentId
  };
});