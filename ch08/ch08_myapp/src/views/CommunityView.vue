<template>
  <div class="container">
    <h1 class="blue-text text-darken-2">
      Community
    </h1>
    <p class="flow-text">
      Explore our community posts and resources.
    </p>
    <hr>
    <figure class="center-align">
      <img
        src="https://picsum.photos/seed/picsum/800/200"
        alt="Community Banner"
        class="responsive-img"
      >
      <figcaption class="flow-text grey-text">
        Join our community and stay updated!
      </figcaption>
    </figure>
    <div class="section">
      <h2 class="indigo-text text-darken-4">
        Community Posts
      </h2>
      <table class="highlight responsive-table">
        <thead>
          <tr>
            <th>ID</th><th>Title</th><th>Date</th><th>Hits</th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="post in communityPosts"
            :key="post.id"
          >
            <td>{{ post.id }}</td><td>{{ post.title }}</td>
            <td>{{ post.resdate }}</td><td>{{ post.hits }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
<script setup>
import { computed } from 'vue';
import { useStore } from 'vuex';
const store = useStore();
const communityPosts = computed(() => store.getters.getCommunityPosts);
</script>    