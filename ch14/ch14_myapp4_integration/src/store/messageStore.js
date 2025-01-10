import { defineStore } from 'pinia';
import axios from 'axios';

export const useMessageStore = defineStore('messageStore', {
  state: () => ({
    sentMessages: [],
    receivedMessages: [],
    conversation: [],
  }),

  actions: {
    async fetchSentMessages(senderId) {
      try {
        const { data } = await axios.get(`http://localhost:8087/api/messages/sent/${senderId}`);
        this.sentMessages = data;
      } catch (error) {
        console.error('Failed to fetch sent messages:', error);
      }
    },

    async fetchReceivedMessages(receiverId) {
      try {
        const { data } = await axios.get(`http://localhost:8087/api/messages/received/${receiverId}`);
        this.receivedMessages = data;
      } catch (error) {
        console.error('Failed to fetch received messages:', error);
      }
    },

    async fetchConversation(senderId, receiverId) {
      try {
        const { data } = await axios.get(`http://localhost:8087/api/messages/conversation`, {
          params: { senderId, receiverId },
        });
        this.conversation = data;
      } catch (error) {
        console.error('Failed to fetch conversation:', error);
      }
    },

    async sendMessage(senderId, receiverId, content) {
      try {
        console.log("senderId: "+senderId);
        console.log("receiverId: "+receiverId);
        console.log("content: "+content);
        const token = localStorage.getItem('auth_token'); // 인증 토큰 가져오기
        const msg = await axios.post(
          'http://localhost:8087/api/messages/send',
          { senderId, receiverId, content },
          {
            headers: {
              Authorization: `Bearer ${token}`, // 인증 헤더 설정
            },
          }
        );
        console.log(msg);
      } catch (error) {
        console.error('Failed to send message:', error);
      }
    },
  },
});