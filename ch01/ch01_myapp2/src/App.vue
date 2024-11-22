<template>
  <div class="app-container">
    <h1>Movie Application</h1>
    <form @submit.prevent="addMovie">
      <div class="grid-container">
        <div class="grid-x grid-padding-x">
          <div class="medium-6 cell">
            <label>영화제목
              <input v-model="title" type="text" placeholder="Enter movie title" required />
            </label>
          </div>
          <div class="medium-6 cell">
            <label>개봉일
              <input v-model="openday" type="date" required />
            </label>
          </div>
        </div>
        <div class="grid-x grid-padding-x">
          <div class="medium-6 cell">
            <label>영화 포스터
              <input @change="handleFileUpload" type="file" accept=".jpg, .png, .gif" required />
            </label>
          </div>
          <div class="medium-6 cell">
            <button type="submit" class="button">Add Movie</button>
          </div>
        </div>
      </div>
    </form>

    <MovieList :movies="movies" @deleteMovie="deleteMovie" @likeMovie="likeMovie" />
  </div>
</template>

<script>
import { ref } from 'vue';
import MovieList from './components/MovieList.vue';

export default {
  components: {
    MovieList,
  },
  setup() {
    const title = ref('');
    const openday = ref('');
    const poster = ref(null);
    const movies = ref([]);

    const handleFileUpload = (event) => {
      poster.value = URL.createObjectURL(event.target.files[0]);
    };

    const addMovie = () => {
      if (!title.value || !openday.value || !poster.value) {
        alert('Please fill in all fields');
        return;
      }

      movies.value.push({
        title: title.value,
        openday: openday.value,
        poster: poster.value,
        favorite: 0,
      });

      // Reset form
      title.value = '';
      openday.value = '';
      poster.value = null;
    };

    const deleteMovie = (index) => {
      movies.value.splice(index, 1);
    };

    const likeMovie = (index) => {
      movies.value[index].favorite += 1;
    };

    return {
      title,
      openday,
      poster,
      movies,
      handleFileUpload,
      addMovie,
      deleteMovie,
      likeMovie,
    };
  },
};
</script>

<style scoped>
.app-container {
  padding: 20px;
}
</style>