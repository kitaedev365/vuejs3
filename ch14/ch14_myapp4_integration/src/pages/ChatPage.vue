<template>
    <div class="chat-page">
      <h2 class="title">실시간 채팅</h2><hr/>
      <div class="chat-container">
        <div class="chat-box">
          <div class="messages" ref="messageList">
            <div class="message" v-for="msg in chats" :key="msg.timestamp">
              <strong>{{ msg.username }}:</strong>
              <p>{{ msg.content }}</p>
            </div>
          </div>
        </div>
        <form @submit.prevent="sendMessage" class="grid-x grid-padding-x">
          <div class="cell small-9">
            <input 
              type="text" 
              v-model="newMessage" 
              class="input-group-field" 
              placeholder="Type a message..." 
              required 
            />
          </div>
          <div class="cell small-3">
            <button type="submit" class="button success expanded">Send</button>
          </div>
        </form>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted, onBeforeUnmount, nextTick } from 'vue';
  import SockJS from 'sockjs-client';
  import Stomp from 'stompjs';
  import { useAuthStore } from '@/store/authStore';
  import { useChatStore } from '@/store/chatStore';
  
  const authStore = useAuthStore();
  const chatStore = useChatStore();
  const newMessage = ref('');
  const chats = ref(chatStore.chats); // Pinia 상태를 ref로 감싸서 반응성 보장
  let stompClient = null;
  
  const connectWebSocket = () => {
    const socket = new SockJS('http://localhost:8087/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, (frame) => {
      console.log('Connected: ' + frame);
      stompClient.subscribe('/topic/messages', (msg) => {
        const newMsg = JSON.parse(msg.body);
        if (newMsg.username && newMsg.content) {
          // 메시지가 중복되지 않으면 추가
          if (!chatStore.chats.some((chat) => chat.timestamp === newMsg.timestamp)) {
            chatStore.addChat(newMsg); // 새 메시지만 추가
          }
        }
      });
    }, (error) => {
      console.error('Error connecting to WebSocket:', error);
    });
  };
  
  const disconnectWebSocket = () => {
    if (stompClient) {
      stompClient.disconnect(() => {
        console.log('Disconnected from WebSocket');
      });
    }
  };
  
  onMounted(() => {
    if (authStore.isAuthenticated && authStore.user) {
      connectWebSocket(); // WebSocket 연결
    }
  });
  
  onBeforeUnmount(() => {
    disconnectWebSocket();
  });
  
  const sendMessage = () => {
    if (authStore.isAuthenticated && authStore.user) {
      if (newMessage.value.trim()) {
        const msg = {
          username: authStore.user.username,
          content: newMessage.value,
          timestamp: new Date().getTime(), // 메시지에 타임스탬프 추가
        };
  
        stompClient.send('/topic/messages', {}, JSON.stringify(msg)); // 메시지 전송
        //chatStore.addChat(msg); // 즉시 메시지 목록에 추가
        newMessage.value = '';
        scrollToBottom();
      }
    } else {
      console.error('User is not authenticated');
    }
  };
  
  const scrollToBottom = () => {
    nextTick(() => {
      const messageList = document.querySelector('.messages');
      if (messageList) {
        messageList.scrollTop = messageList.scrollHeight;
      }
    });
  };
  </script>