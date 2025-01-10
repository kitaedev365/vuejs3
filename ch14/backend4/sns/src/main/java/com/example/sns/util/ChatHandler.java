package com.example.sns.util;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.example.sns.domain.ChatMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ChatHandler extends TextWebSocketHandler {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws InterruptedException, IOException {
        try {
            // 클라이언트에서 수신한 메시지 처리
            String payload = message.getPayload();
            ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);

            // 메시지를 받은 후, 모든 클라이언트로 전달
            for (WebSocketSession webSocketSession : sessions) {
                if (webSocketSession.isOpen()) {
                    webSocketSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(chatMessage)));
                }
            }
        } catch (IOException e) {
            System.err.println("Error processing message: " + e.getMessage());
            e.printStackTrace(); // 오류 로그를 출력
            session.close(CloseStatus.SERVER_ERROR);
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 사용자의 인증 상태 확인
        String username = (String) session.getAttributes().get("username");
        if (username == null) {
            session.close(CloseStatus.NOT_ACCEPTABLE); // 인증되지 않은 사용자는 연결을 닫음
        } else {
            // 세션을 관리하는 리스트에 추가
            sessions.add(session);
            System.out.println("User connected: " + username);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // WebSocket 연결 종료 후
        sessions.remove(session);  // 연결 종료된 세션을 목록에서 제거
        System.out.println("Connection closed: " + session.getId() + " with status " + status);
    }
}