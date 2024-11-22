import { createStore } from 'vuex';
import axios from 'axios';

const store = createStore({
  state: {
    posts: [],
  },
  mutations: {
    setPosts(state, posts) {
      state.posts = posts;
    },
  },
  actions: {
    async fetchPosts({ commit }) {
      try {
        const response = await axios.get('http://localhost:8081/api/board');
        commit('setPosts', response.data);
      } catch (error) {
        console.error('Error fetching posts:', error);
        const dummyPosts = Array.from({ length: 11 }, (_, index) => ({
          id: `dummy-${index + 1}`,         // 임의의 ID
          title: `테스트 제목 ${index + 1}`, // 더미 제목
          content: `테스트 글의 내용 ${index + 1}.` // 더미 내용
        }));
        commit('setPosts', dummyPosts);
      }
    },
  },
  getters: {
    allPosts(state) {
      return state.posts;
    },
  },
});

export default store;