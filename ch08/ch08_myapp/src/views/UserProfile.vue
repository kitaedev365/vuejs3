<template>
  <div class="container">
    <h1 class="center-align">
      User Profile
    </h1>
    <hr>
    <p
      v-if="props.id"
      class="flow-text"
    >
      User ID: {{ props.id }}
    </p>
    <p
      v-else
      class="flow-text red-text"
    >
      No User ID Provided
    </p>
      
    <div class="row">
      <!-- Profile Picture Section -->
      <div class="col s12 m4 center-align">
        <figure>
          <img
            src="https://via.placeholder.com/150"
            alt="User Profile Picture"
            class="circle responsive-img"
          >
          <figcaption class="flow-text">
            Profile Picture
          </figcaption>
        </figure>
      </div>
        
      <!-- Basic Information Section -->
      <div class="col s12 m8">
        <h5>Basic Information</h5>
        <ul class="collection">
          <li class="collection-item">
            <strong>Name:</strong> 김 기 태
          </li>
          <li class="collection-item">
            <strong>Email:</strong> kim@gmail.com
          </li>
          <li class="collection-item">
            <strong>Joined:</strong> January 1, 2021
          </li>
        </ul>
      </div>
    </div>
  
    <!-- About Section -->
    <div class="row">
      <div class="col s12">
        <h5>About</h5>
        <p class="flow-text">
          나의 소개에 관한 글입니다. Vue.js와 Materialize CSS로 만든 깔끔한 사용자 프로필 페이지 예제입니다.
        </p>
      </div>
    </div>
  
    <!-- Recent Activity Section -->
    <div class="row">
      <div class="col s12">
        <h5>Recent Activity</h5>
        <ul class="collection">
          <li class="collection-item">
            Posted a new article on Vue.js Composition API
          </li>
          <li class="collection-item">
            Commented on "Materialize CSS Overview"
          </li>
          <li class="collection-item">
            Updated profile picture
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>
  <script setup>
  const props = defineProps({
    id: {
      type: String,
      default: '',
    },
  });
  </script>