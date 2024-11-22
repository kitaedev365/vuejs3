import { defineStore } from 'pinia';
import axios from 'axios';
export const useContentStore = defineStore('content', {
  state: () => ({
    contents: [],
    galleries: [],
    selectedContent: null,
    selectedGallery: null,
  }),
  actions: {
    async fetchContents() {
      const response = await axios.get('http://localhost:8086/api/contents');
      this.contents = response.data;
    },
    async fetchContentById(id) {
      const response = await axios.get(`http://localhost:8086/api/contents/${id}`);
      this.selectedContent = response.data;
      return response.data;
    },
    async fetchGalleryByContentId(id) {
      const response = await axios.get(`http://localhost:8086/api/galleries/content/${id}`);
      this.selectedGallery = response.data; // response.data는 배열이어야 함
      return response.data;
    },
    async addContent(content) {
      await axios.post('http://localhost:8086/api/contents', content);
      this.fetchContents();
    },
    async updateContent(id, content) {
      await axios.put(`http://localhost:8086/api/contents/${id}`, content);
      this.fetchContents();
    },
    async deleteContent(id) {
      await axios.delete(`http://localhost:8086/api/contents/${id}`);
      this.fetchContents();
    },
  },
});