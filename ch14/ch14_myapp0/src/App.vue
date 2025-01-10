<template>
  <div class="container">
    <nav :class="['navbar', isFixed ? 'is-fixed-top' : 'is-absolute']">
      <div class="container">
        <ul class="menu">
          <li><a @click.prevent="scrollTo('intro')">Intro</a></li>
          <li><a @click.prevent="scrollTo('skill')">Skill</a></li>
          <li><a @click.prevent="scrollTo('gallery')">Gallery</a></li>
          <li><a @click.prevent="scrollTo('contact')">Contact</a></li>
        </ul>
      </div>
    </nav>

    <!-- Pages -->
    <section id="intro" class="page intro">
      <h1 class="title">About Me</h1>
      <div class="columns">
        <div class="column is-half">
          <img src="/assets/profile1.jpg" alt="Caricature">
        </div>
        <div class="column is-half">
          <ul>
            <li class="item"><strong>Name:</strong> KiTae Kim</li>
            <li class="item"><strong>Profession:</strong> Fullstack Developer</li>
            <li class="item"><strong>Hobby:</strong> Coding, Gaming</li>
            <li class="item"><strong>Personal History:</strong> 16 Year</li>
            <li class="item"><strong>Specialty:</strong> Braiding</li>
            <li class="item"><strong>Education:</strong> Korea Tech Univercity</li>
          </ul>
        </div>
      </div>
    </section>

    <section id="skill" class="page skill">
      <h1 class="title">Skills</h1>
      <div class="columns">
        <div class="column" v-for="skill in skills" :key="skill.name">
          <div class="donut">
            <svg viewBox="0 0 36 36">
              <path
                class="circle"
                :stroke-dasharray="`${skill.percentage}, 100`"
                d="M18 2.0845
                   a 15.9155 15.9155 0 0 1 0 31.831
                   a 15.9155 15.9155 0 0 1 0 -31.831"
              />
            </svg>
            <p>{{ skill.name }}</p>
            <h2 class="percent">{{ skill.percentage }}%</h2>
          </div>
        </div>
      </div>
    </section>

    <section id="gallery" class="page gallery">
      <h1 class="title">Gallery</h1>
      <div class="columns is-multiline">
        <div class="column is-one-third" v-for="project in projects" :key="project.title">
          <div class="card">
            <a :href="project.url">
              <div class="card-image">
                <figure class="image">
                  <img :src="project.image" :alt="project.title">
                </figure>
              </div>
              <div class="card-content">
                <h2 class="title">{{ project.title }}</h2>
                <p v-html="project.description"></p>
              </div>
            </a>
          </div>
        </div>
      </div>
    </section>

    <section id="contact" class="page contact">
      <h1 class="title">Contact</h1>
      <form @submit.prevent="submitForm">
        <textarea v-model="message" class="textarea" placeholder="Write your message"></textarea>
        <button class="button is-primary">Send</button>
      </form>
    </section>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';

const isFixed = ref(false);
const skills = ref([
  { name: 'Java Spring', percentage: 90 },
  { name: 'Python', percentage: 85 },
  { name: 'HTML/CSS', percentage: 95 },
  { name: 'JavaScript', percentage: 90 },
  { name: 'Vue.js', percentage: 80 },
]);
const projects = ref([
  {
    image: '/assets/sub1.jpg',
    title: 'E-commerce 플랫폼',
    description: `
      - 회원가입 및 로그인: 사용자의 인증 및 계정 관리<br>
      - 상품 CRUD: 상품 목록 조회, 등록, 수정, 삭제<br>
      - 사용 기술: Vue CLI, Vue Router, Pinia, Bulma
    `,
    url: '/sub1',
  },
  {
    image: '/assets/sub2.jpg',
    title: 'Project Management Tool',
    description: `
      - 회원 관리: 로그인 및 회원정보 수정<br>
      - 대시보드: 프로젝트 진행 상황 시각화<br>
      - 사용 기술: Vite, Vue Router, Pinia, Bootstrap
    `,
    url: '/sub2',
  },
  {
    image: '/assets/sub3.jpg',
    title: 'Content Management System',
    description: `
      - 콘텐츠 관리: 콘텐츠 등록, 수정, 삭제<br>
      - 사용 기술: Vue CLI, Vue Router, Pinia, PureCSS
    `,
    url: '/sub3',
  },
  {
    image: '/assets/sub4.jpg',
    title: 'Social Media 플랫폼',
    description: `
      - 게시물 관리: 게시물 CRUD, 미디어 파일 업로드<br>
      - 사용 기술: Vite, Vue Router, Pinia, Foundation CSS
    `,
    url: '/sub4',
  },
  {
    image: '/assets/sub5.jpg',
    title: '온라인 강의 플랫폼',
    description: `
      - 강의 관리: 강의 등록, 수강 예약<br>
      - 사용 기술: Vite, Vue Router, Pinia, Materialize
    `,
    url: '/sub5',
  },
]);

const message = ref('');

const scrollTo = (id) => {
  document.getElementById(id).scrollIntoView({ behavior: 'smooth' });
};

const submitForm = () => {
  alert(`Message sent: ${message.value}`);
  message.value = '';
};

onMounted(() => {
  window.addEventListener('scroll', () => {
    isFixed.value = window.scrollY > window.innerHeight;
  });
});
</script>

<style scoped>
.title {
  text-align: center;
  font-size: 2rem;
  padding-bottom: 1rem;  
  padding-top: 1.6rem;
}
.page {
  min-height: 100vh;
  padding: 5vh 2rem;
  box-sizing: border-box;
}
.navbar {
  background: #fcba03;
  transition: position 0.3s;
  box-shadow: 0px 5px 25px #ccc; 
}
.menu {
  display: flex;
}
.menu li {
  padding: 10px 24px;
  cursor: pointer;
}
.menu li a {
  cursor: pointer;
  font-weight: bold;
}
li.item {
  font-size: 1.2rem;
  line-height: 2.4;
}
.is-fixed-top {
  position: fixed;
  top: 0;
  width: 100%;
  z-index: 1000;
}
.is-absolute {
  position: absolute;
  top: 0;
  width: 100%;
}
.columns {
  display: flex;
  flex-wrap: wrap;
}
.column.is-one-third {
  width:30%;
  margin: 1.5%;
  min-width: 320px;
  min-height: 340px;
  padding-bottom: 14px;
} 
.column.is-one-third .title {
  font-size: 1.2rem;
}
.columns .column.is-half {
  max-width: 50%;
  box-sizing: border-box;
  padding: 8vh;
  padding-top: 15vh;
}
.columns .column.is-half:first-child {
  padding-top: 1vh;
}
.column.is-half img {
  display: block;
  width: 80%;
  margin: 10%;
}
.columns .column.is-half img {
  width: 100%;
  margin: 0;
}
.column.is-one-third .card {
  min-height: 340px;
  z-index: 99;
}
.column .card .card-content p {
  padding-left: 16px;
}
.column .card .image {
  margin: 0;
  padding: 0;
}
.column .card .image img {
  display: block;
  width: 100%;
}
.donut {
  text-align: center;
  padding-top: 3vw;
  position: relative;
}
.donut .percent {
  text-align: center;
  position: absolute;
  top: 135px;
  width: 100%;
  font-size: 1.4rem;
}
.donut svg {
  width: 15vw;
  height: 15vw;
  margin: 1.8vw;
}
.donut p {
  font-weight: bold;
  font-size: 1.5rem;
}
.circle {
  fill: none;
  stroke: #00d1b2;
  stroke-width: 2.8;
  stroke-linecap: round;
  animation: progress 5s ease-out both;
  animation-iteration-count: infinite;
}
.card {
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
}
.map {
  height: 300px;
  background-color: lightgray;
}
@keyframes progress {
  0% {
    stroke-dasharray: 0, 100;
  }
  50% {
    stroke-dasharray: 0, 100;
  }
}
.textarea {
  display:block;
  width: 80%;
  margin: 14px auto;
  min-width: 320px;
  min-height: 320px;
}
.button {
  display: block;
  padding: 0.8rem 2rem;
  outline: 0;
  border: 0;
  cursor: pointer;
  margin: 10px auto;
  font-weight: bold;
}
.is-primary {
  background-color: #00d1b2;
  color: #fff;
}
</style>