package com.example.sns.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.sns.domain.ChatMessage;

@Controller
public class ChatController {

	private static final Logger log = LoggerFactory.getLogger(ChatController.class);
	
    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat")  // 클라이언트는 STOMP 메시지로 이 경로를 사용
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(ChatMessage message) {
    	log.info("Chat data : "+message.toString());
        return message; // 메시지를 다시 브로드캐스트
    }

    @PostMapping("/api/chat")  // REST API로 전송된 메시지 처리
    public ResponseEntity<Void> sendChatMessage(@RequestBody ChatMessage message) {
        messagingTemplate.convertAndSend("/topic/messages", message);
        return ResponseEntity.ok().build();
    }
}