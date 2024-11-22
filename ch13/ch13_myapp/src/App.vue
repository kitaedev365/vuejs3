<template>
  <div id="app">
    <!-- 네비게이션 바 -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <div class="container">
        <a class="navbar-brand" href="#">Board App</a>
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav">
            <li class="nav-item">
              <router-link to="/" class="nav-link">Home</router-link>
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <!-- 페이지 콘텐츠 -->
    <main class="container my-4">
      <router-view />
    </main>
  </div>
</template>

<script setup>
// <script setup>을 사용해 별도 코드 작성 없이 템플릿만 정의할 수 있습니다.
</script>

<style>
/* 기본 스타일 */
#app {
  font-family: Arial, sans-serif;
  color: #333;
}

.navbar {
  margin-bottom: 20px;
}
</style>