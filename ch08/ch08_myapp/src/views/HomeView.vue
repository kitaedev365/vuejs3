<template>
  <div class="container">
    <h1 class="center-align">
      Welcome to Home Page
    </h1>
    <hr>
    <figure class="center-align">
      <img
        src="https://picsum.photos/seed/picsum/800/200"
        alt="Banner Image"
        class="responsive-img"
      >
    </figure>
    <table class="highlight centered responsive-table">
      <thead>
        <tr>
          <th>ID</th><th>Title</th><th>Date</th><th>Hits</th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="post in recentPosts"
          :key="post.id"
        >
          <td>{{ post.id }}</td> <td>{{ post.title }}</td>
          <td>{{ post.resdate }}</td> <td>{{ post.hits }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
<script setup>
import { ref } from 'vue';
const communityPosts = ref([
  { id: 1, title: 'First Post', contents: 'This is the first post.', resdate: '2024-10-23', hits: 102 },
  { id: 2, title: 'Second Post', contents: 'This is the second post.', resdate: '2024-10-24', hits: 76 },
  { id: 3, title: 'Third Post', contents: 'This is the third post.', resdate: '2024-10-25', hits: 89 },
  { id: 4, title: 'Fourth Post', contents: 'This is the fourth post.', resdate: '2024-10-26', hits: 65 },
  { id: 5, title: 'Fifth Post', contents: 'This is the fifth post.', resdate: '2024-10-27', hits: 150 },
]);
const recentPosts = ref(communityPosts.value.slice(0, 3));
</script>  