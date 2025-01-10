package com.example.sns.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sns.domain.MessageDto;
import com.example.sns.domain.RequestMessageDto;
import com.example.sns.service.MessageService;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    // 메시지 전송
    @PostMapping("/send")
    public ResponseEntity<MessageDto> sendMessage(@RequestBody RequestMessageDto rDto) {
        MessageDto message = messageService.sendMessage(rDto.getSenderId(), rDto.getReceiverId(), rDto.getContent());
        return ResponseEntity.ok(message);
    }

    // 특정 대화 가져오기 (두 사용자 간 대화 내역)
    @GetMapping("/conversation")
    public ResponseEntity<List<MessageDto>> getConversation(
            @RequestParam("senderId") Long senderId,
            @RequestParam("receiverId") Long receiverId) {
        List<MessageDto> conversation = messageService.getConversation(senderId, receiverId);
        return ResponseEntity.ok(conversation);
    }

    // 받은 메시지 가져오기
    @GetMapping("/received/{receiverId}")
    public ResponseEntity<List<MessageDto>> getReceivedMessages(@PathVariable("receiverId") Long receiverId) {
        List<MessageDto> messages = messageService.getReceivedMessages(receiverId);
        return ResponseEntity.ok(messages);
    }
    
    // 보낸 메시지 가져오기
    @GetMapping("/sent/{sendId}")
    public ResponseEntity<List<MessageDto>> getSentMessages(@PathVariable("sendId") Long sendId) {
        List<MessageDto> messages = messageService.getSendMessages(sendId);
        return ResponseEntity.ok(messages);
    }
}