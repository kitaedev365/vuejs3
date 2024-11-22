<template>
  <div class="container center-align">
    <h1 class="red-text">
      404 - Page Not Found
    </h1>
    <p class="flow-text">
      Sorry, the page you are looking for does not exist.
    </p>
    <router-link
      to="/"
      class="btn waves-effect waves-light"
    >
      Go to Home
    </router-link>
    <hr>
    <figure>
      <img
        src="https://via.placeholder.com/600x300?text=404+Error+-+Page+Not+Found"
        alt="404 Error"
        class="responsive-img"
      >
      <figcaption class="flow-text grey-text">
        요청하신 경로의 페이지가 존재하지 않습니다.
      </figcaption>
    </figure>
  </div>
</template>
<script setup>
</script>    