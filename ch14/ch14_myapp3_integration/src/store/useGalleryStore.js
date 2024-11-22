import { defineStore } from 'pinia';
import axios from 'axios';
export const useGalleryStore = defineStore('gallery', {
  state: () => ({
    galleries: [], gallery: {},
    selectedGallery: null, selGallery: null
  }),
  actions: {
    async fetchGalleries() {
      const response = await axios.get('http://localhost:8086/api/galleries');
      this.galleries = response.data;
    },
    async fetchGalleryById(id) {
      const response = await axios.get(`http://localhost:8086/api/galleries/${id}`);
      this.selGallery = response.data;
      return response.data;
    },
    async addGallery(gallery) {
      console.log("Store Receive: "+gallery.contentId);
      await axios.post('http://localhost:8086/api/galleries/add', gallery);
      this.fetchGalleries();
    },
    async updateGallery(id, gallery) {
      await axios.put(`http://localhost:8086/api/galleries/${id}`, gallery);
      this.fetchGalleries();
    },
    async deleteGallery(id) {
      await axios.delete(`http://localhost:8086/api/galleries/${id}`);
      this.fetchGalleries();
    },
  },
});