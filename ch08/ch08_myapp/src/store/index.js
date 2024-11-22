import { createStore } from 'vuex';
export default createStore({
  state: {
    communityPosts: [
      { id: 1, title: 'Welcome to Our Community', resdate: '2024-10-20', hits: 150 },
      { id: 2, title: 'Vue.js Tips and Tricks', resdate: '2024-10-18', hits: 120 },
      { id: 3, title: 'Materialize CSS for Beginners', resdate: '2024-10-15', hits: 80 },
      { id: 4, title: 'Understanding Composition API', resdate: '2024-10-12', hits: 200 },
      { id: 5, title: 'Community Guidelines', resdate: '2024-10-10', hits: 95 },
      { id: 6, title: 'Q&A Session', resdate: '2024-10-08', hits: 180 },
    ],
  },
  getters: {
    getCommunityPosts(state) { return state.communityPosts; },
  },
});
