const express = require('express');
const http = require('http');
const { Server } = require('socket.io');

const app = express();
const server = http.createServer(app);
const io = new Server(server, {
  cors: {
    origin: '*',
    methods: ['GET', 'POST'],
  },
});

const PORT = 7000;

// Socket.IO 연결 설정
io.on('connection', (socket) => {
  console.log('User connected:', socket.id);

  // 클라이언트로부터 메시지 수신
  socket.on('chat message', (msg) => {
    io.emit('chat message', msg); // 모든 클라이언트에 메시지 브로드캐스트
  });

  socket.on('disconnect', () => {
    console.log('User disconnected:', socket.id);
  });
});

server.listen(PORT, () => {
  console.log(`Server running on http://localhost:${PORT}`);
});