<template>
  <div class="movie-list">
    <div class="grid-container">
      <div class="grid-x grid-margin-x small-up-1 medium-up-3">
        <div v-for="(movie, index) in movies" :key="index" class="cell">
          <div class="card">
            <img :src="movie.poster" alt="Movie Poster" />
            <div class="card-section">
              <h4>{{ movie.title }}</h4>
              <p>Release Date: {{ movie.openday }}</p>
              <p>Favorite: {{ movie.favorite }}</p>
              <div class="button-group">
                <button @click="$emit('likeMovie', index)" class="button success">Like</button>
                <button @click="$emit('deleteMovie', index)" class="button alert">Delete</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    movies: Array,
  },
};
</script>

<style scoped>
.card img {
  width: 100%;
  height: auto;
}
</style>