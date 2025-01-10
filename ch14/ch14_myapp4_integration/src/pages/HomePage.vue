<template>
    <div class="home">
      <h3 class="title">SNS 애플리케이션에 오신 것을 환영합니다.</h3><hr>
      <div class="msgbox">
        <h5 class="title">회원 전용 채팅창 제공, 회원 전용 쪽지함 제공, 회원 공용 공간 제공</h5>
      </div>
      <div class="grid-container">
        <div class="grid-x grid-padding-x">
          <!-- 게시물 목록을 반복해서 표시 -->
          <div class="cell small-12 medium-6 large-4" v-for="post in posts" :key="post.id">
            <div class="card">
              <div class="card-divider">
                <!-- 게시물 제목 -->
                {{ post.title }}
              </div>
              <div class="card-body">
                <!-- 게시물 내용 -->
                <p>{{ post.content }}</p>
              </div>
              <div class="card-footer">
                <!-- 게시물 상세보기 버튼 -->
                <router-link :to="`/posts/${post.id}`" class="button small">
                  Read more
                </router-link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  <script setup>
import { ref, onMounted } from 'vue';
import { usePostStore } from '@/store/postStore';

const posts = ref([]);

const loadPosts = async () => {
  try {
    const postStore = usePostStore();
    posts.value = await postStore.fetchPosts();  // 게시물 목록을 비동기적으로 로드
  } catch (error) {
    console.error('게시물 목록을 불러오는 중 오류 발생:', error);
  }
};

onMounted(loadPosts);  // 컴포넌트가 마운트될 때 게시물 목록을 불러옴
  </script>
  <style scoped>
  .home {
    padding: 36px;
  }
  /* 카드 스타일 */
  .card {
    margin-bottom: 20px;
  }
  .card-divider {
    font-size: 1.2rem;
    font-weight: bold;
  }
  .card-body {
    font-size: 1rem;
  }
  .card-footer {
    text-align: right;
  }
  </style>