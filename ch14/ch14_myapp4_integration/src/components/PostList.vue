<template>
  <div class="post-list">
    <h3>Post List</h3>
    <hr />
    <div v-if="!posts.length" class="empty-state">
      <p>No posts available.</p>
    </div>
    <div v-else>
      <div class="post-item" v-for="post in posts" :key="post.id">
        <PostItem :post="post" />
      </div>
    </div>
    <hr/>
    <div class="cell small-12 medium-6 large-4">
      <router-link to="/post/new" class="button success">글쓰기</router-link>
    </div>
  </div>
</template>
<script setup>
import { ref, onMounted, computed } from 'vue';
import { usePostStore } from '@/store/postStore';
import { useAuthStore } from '@/store/authStore';
import PostItem from '@/components/PostItem.vue';

const postStore = usePostStore();
const authStore = useAuthStore();
const posts = computed(() => postStore.posts);

onMounted(async () => {
  if (authStore.isAuthenticated) {
    await postStore.fetchPosts(); // fetchPosts는 async 이므로, 데이터가 준비될 때까지 기다림
  }
});
</script>
  <style scoped>
  .post-list {
    padding: 20px;
  }
  .empty-state {
    text-align: center;
    background-color: #f9f9f9;
    padding: 20px;
    border-radius: 5px;
  }
  .post-item {
    margin-bottom: 20px;
  }
  </style>  