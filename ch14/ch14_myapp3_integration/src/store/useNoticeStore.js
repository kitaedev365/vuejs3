import { defineStore } from 'pinia';
import axios from 'axios';
export const useNoticeStore = defineStore('notice', {
  state: () => ({
    notices: [],
    selectedNotice: { title: '', content: '', hits: 0, createdAt: '', updatedAt: '' }
  }),
  actions: {
    async fetchNotices() {
      const response = await axios.get('http://localhost:8086/api/notices');
      this.notices = response.data;
    },
    async fetchNoticeById(id) {
      const response = await axios.get(`http://localhost:8086/api/notices/${id}`);
      if (!response.data) {
        throw new Error(`Notice with id ${id} not found`);
      }
      this.selectedNotice = response.data;
      return response.data;
    },
    async addNotice(notice) {
      await axios.post('http://localhost:8086/api/notices', notice);
      this.fetchNotices();
    },
    async updateNotice(id, notice) {
      await axios.put(`http://localhost:8086/api/notices/${id}`, notice);
      this.fetchNotices();
    },
    async deleteNotice(id) {
      await axios.delete(`http://localhost:8086/api/notices/${id}`);
      this.fetchNotices();
    }
  }
});
