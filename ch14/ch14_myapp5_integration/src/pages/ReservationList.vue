<template>
  <DefaultLayout>
    <div class="container">
      <h3 class="center">Reservations</h3><hr/>
      <ul class="collection">
        <li v-for="reservation in res" :key="reservation.id" class="collection-item">
          <router-link :to="`/reservations/${reservation.id}`">{{ reservation.courseTitle }}</router-link>
        </li>
      </ul>
      <router-link to="/reservations/new" class="btn waves-effect waves-light">수강 신청</router-link>
    </div>  
  </DefaultLayout>
</template>
<script setup>
import { onMounted, computed, ref } from 'vue';
import { useReservationStore, useUserStore } from '@/store';
const reservationStore = useReservationStore();
const userStore = useUserStore();
const res = computed(() => reservationStore.res);
const user = computed(() => userStore.user);
onMounted(async () => {
  const username = localStorage.getItem("username");
  await userStore.fetchUser(username);
  const userId = user.value.id;
  await reservationStore.getReaervationsByCourseIdAndStudentId(userId);
  console.log(res.value);
});
</script>
<style scoped>
ul {
  margin-top: 20px;
}
</style>  