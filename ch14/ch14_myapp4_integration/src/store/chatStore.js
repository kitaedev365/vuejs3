import { defineStore } from 'pinia';
import axios from 'axios';

export const useChatStore = defineStore('chat', {
  state: () => ({
    chats: [], // 메시지 목록
    msg: {},
    conversation: [], // 두 사용자의 대화 내용
  }),
  actions: {
    async addChat(chat) {
      // 중복 메시지 체크
      if (!this.chats.some((existingChat) => existingChat.timestamp === chat.timestamp)) {
        try {
          // 서버로 메시지 전송
          await axios.post('http://localhost:8087/api/chat', chat);
          // 상태에 메시지 추가
          console.log(chat);
          this.chats.push(chat);
        } catch (error) {
          console.error('Failed to send message:', error);
          throw error;
        }
      }
    },
    setChats(newChats) {
      this.chats = newChats;
    }
  }
});