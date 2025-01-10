import { defineStore } from 'pinia'
import axios from 'axios'
export const usePostStore = defineStore('post', {
  state: () => ({
    posts: [],  // 게시물 목록
    currentPost: null,  // 선택된 게시물
    post: {},
  }),
  actions: {
    async fetchPosts() {      // 모든 게시물 조회
      try {
        const response = await axios.get('http://localhost:8087/api/posts')
        this.posts = response.data
      } catch (error) {
        console.error('Failed to fetch posts:', error)
      }
    },
    async fetchPostsByUser(userId) {      // 특정 사용자의 게시물 조회
      try {
        const response = await axios.get(`http://localhost:8087/api/posts/user/${userId}`)
        this.posts = response.data
      } catch (error) {
        console.error('Failed to fetch user posts:', error)
      }
    },
    async fetchPostById(postId) {      // 게시물 상세 조회
      try {
        const response = await axios.get(`http://localhost:8087/api/posts/${postId}`)
        this.currentPost = response.data
        this.post = response.data
      } catch (error) {
        console.error('Failed to fetch post:', error)
      }
    },
    async createPost(formData) {
      try {
        const response = await axios.post('http://localhost:8087/api/posts/new', formData, {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
        });
    
        this.posts.push(response.data); // 새 게시물 추가
      } catch (error) {
        console.error('Failed to create post:', error);
        throw error;
      }
    },
    async updatePost(postId, postData) {     // 게시물 수정
      try {
        const response = await axios.put(`http://localhost:8087/api/posts/${postId}`, postData)
        const index = this.posts.findIndex(post => post.id === postId)
        if (index !== -1) {
          this.posts[index] = response.data // 수정된 게시물로 업데이트
        }
      } catch (error) {
        console.error('Failed to update post:', error)
      }
    },
    async deletePost(postId) {      // 게시물 삭제
      try {
        await axios.delete(`http://localhost:8087/api/posts/${postId}`)
        this.posts = this.posts.filter(post => post.id !== postId) // 게시물 목록에서 삭제
      } catch (error) {
        console.error('Failed to delete post:', error)
      }
    }
  }
})