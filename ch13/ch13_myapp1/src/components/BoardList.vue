<template>
  <div class="container">
    <h1 class="my-4">게시판 목록</h1>
    <hr/>
    <table class="table table-striped">
      <thead>
        <tr>
          <th>#</th>
          <th>Title</th>
          <th>Content</th>
        </tr>
      </thead>
      <tbody>
        <!-- 현재 페이지에 해당하는 게시글 목록을 렌더링 -->
        <tr v-for="(post, index) in paginatedPosts" :key="post.id">
          <td>{{ (currentPage - 1) * postsPerPage + index + 1 }}</td>
          <td>{{ post.title }}</td>
          <td>{{ post.content }}</td>
        </tr>
      </tbody>
    </table>

    <!-- 페이지네이션 버튼 -->
    <nav aria-label="Page navigation" class="my-4">
      <ul class="pagination justify-content-center">
        <li class="page-item" :class="{ disabled: currentPage === 1 }">
          <button class="page-link" @click="prevPage" :disabled="currentPage === 1">Previous</button>
        </li>
        <li class="page-item" v-for="page in totalPages" :key="page" :class="{ active: page === currentPage }">
          <button class="page-link" @click="goToPage(page)">{{ page }}</button>
        </li>
        <li class="page-item" :class="{ disabled: currentPage === totalPages }">
          <button class="page-link" @click="nextPage" :disabled="currentPage === totalPages">Next</button>
        </li>
      </ul>
    </nav>
  </div>
</template>

<script setup>
import { computed, ref, onMounted } from 'vue';
import { useStore } from 'vuex';

const store = useStore();

// 게시글 상태를 가져옵니다.
const posts = computed(() => store.getters.allPosts);

// 페이지네이션 관련 설정
const currentPage = ref(1);
const postsPerPage = 5; // 페이지 당 게시글 수

// 페이지네이션을 위한 계산된 게시글 목록
const paginatedPosts = computed(() => {
  const start = (currentPage.value - 1) * postsPerPage;
  const end = start + postsPerPage;
  return posts.value.slice(start, end);
});

// 총 페이지 수 계산
const totalPages = computed(() => Math.ceil(posts.value.length / postsPerPage));

// 페이지네이션 기능 메소드
const goToPage = (page) => {
  currentPage.value = page;
};

const nextPage = () => {
  if (currentPage.value < totalPages.value) {
    currentPage.value++;
  }
};

const prevPage = () => {
  if (currentPage.value > 1) {
    currentPage.value--;
  }
};

// 컴포넌트가 마운트될 때 Vuex 액션을 통해 게시글을 불러옵니다.
onMounted(() => {
  store.dispatch('fetchPosts');
});
</script>

<style scoped>
.container {
  max-width: 800px;
}
.table {
  margin-top: 20px;
}
.pagination {
  margin-top: 20px;
}
</style>