<template>
  <div id="app" class="d-flex flex-column min-vh-100">
    <HeaderComp />
    <main class="flex-fill container mt-4">
      <router-view />
    </main>
    <FooterComp />
  </div>
</template>
<script setup>
import HeaderComp from './components/HeaderComp.vue'
import FooterComp from './components/FooterComp.vue'
</script>  