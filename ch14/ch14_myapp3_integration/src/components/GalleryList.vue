<template>
    <div>
      <h1 class="pure-u-1">Galleries</h1>
      <ul class="pure-menu-list">
        <li v-for="gallery in galleries" :key="gallery.id" class="pure-menu-item">
          <router-link :to="`/galleries/${gallery.id}`" class="pure-menu-link">
            {{ gallery.filePath }}
          </router-link>
        </li>
      </ul>
      <router-link to="/galleries/new" class="pure-button pure-button-primary">Add New Gallery</router-link>
    </div>
  </template>
  <script setup>
  import { computed } from 'vue';
  import { useGalleryStore } from '../store/useGalleryStore';
  import { onMounted } from 'vue';
  const galleryStore = useGalleryStore();
  onMounted(() => {
    galleryStore.fetchGalleries();
  });
  const galleries = computed(() => galleryStore.galleries);
  </script>
  