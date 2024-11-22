<template>
  <DefaultLayout>
    <div class="container">
      <h3 class="center">{{ isAdmin ? '수강 과정 관리' : '수강 과정 목록' }}</h3><hr/>
      <div v-if="isAdmin">
        <table class="table">
          <thead>
            <tr><th>수강번호</th><th>수강 과정</th><th>수강생</th><th>상태관리</th></tr>
          </thead>
          <tbody>
            <tr v-for="enrollment in enrollments" :key="enrollment.id" class="collection-item">
              <td>{{ enrollment.id }}</td>
              <td><router-link :to="`/enrollments/${enrollment.id}`">{{ enrollment.courseTitle }}</router-link></td>
              <td>{{ enrollment.studentName }}</td>
              <td>
                <span v-if="enrollment.status === 'ENROLLED'">수강중</span>
                <span v-else>수강 철회 신청중 &nbsp; &nbsp; 
                  <button @click="`deleteEnrollment(${enrollment.id})`" class="btn red waves-effect waves-light">수강 삭제</button>
                </span>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div v-else>
        <ul class="collection">
          <li v-for="enrollment in enrolls" :key="enrollment.id" class="collection-item">
            <router-link :to="`/enrollments/${enrollment.id}`">{{ enrollment.courseTitle }}</router-link>
          </li>
        </ul>
      </div>
    </div>
  </DefaultLayout>
</template>
<script setup>
import { onMounted, computed, ref } from 'vue';
import { useEnrollmentStore, useUserStore } from '@/store';
import enrollmentService from '@/services/enrollmentService';
const enrollmentStore = useEnrollmentStore();
const userStore = useUserStore();
const enrolls = computed(() => enrollmentStore.enrolls);
const enrollments = computed(() => enrollmentStore.enrollments);
const user = computed(() => userStore.user);
const role = localStorage.getItem('role');
const isAdmin = computed(() => role === 'ADMIN');
onMounted(async () => {
  const username = localStorage.getItem("username");
  await userStore.fetchUser(username);
  const userId = user.value.id;
  await enrollmentStore.getEnrollmentsByCourseIdAndStudentId(userId);
  await enrollmentStore.fetchEnrollments();
});
async function deleteEnrollment(enrollmentId) {
  if (confirm('정말 수강내역을 삭제하시겠습니까?')) {
    await enrollmentService.deleteEnrollment(enrollmentId);
    router.push('/enrollments');
  }
}
</script>
<style scoped>
ul {
  margin-top: 20px;
}
</style>  