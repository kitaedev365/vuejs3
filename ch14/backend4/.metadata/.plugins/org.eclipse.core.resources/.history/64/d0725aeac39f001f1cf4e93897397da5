package com.example.sns.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sns.domain.MessageDto;
import com.example.sns.entity.Message;
import com.example.sns.entity.User;
import com.example.sns.repository.MessageRepository;

@Service
@Transactional
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserService userService; // For verifying user existence

    public MessageService(MessageRepository messageRepository, UserService userService) {
        this.messageRepository = messageRepository;
        this.userService = userService;
    }

    // 메시지 전송
    public MessageDto sendMessage(Long senderId, Long receiverId, String content) {
    	
        User sender = userService.getUserById(senderId);
        User receiver = userService.getUserById(receiverId);

        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(content);

        Message savedMessage = messageRepository.save(message);
        return toDto(savedMessage);
    }

    // 특정 대화 가져오기 (두 사용자 간의 메시지)
    public List<MessageDto> getConversation(Long senderId, Long receiverId) {
        List<Message> messages = messageRepository.findBySenderIdAndReceiverIdOrderBySentAtAsc(senderId, receiverId);
        return messages.stream().map(this::toDto).collect(Collectors.toList());
    }

    // 받은 메시지 가져오기
    public List<MessageDto> getReceivedMessages(Long receiverId) {
        List<Message> messages = messageRepository.findByReceiverIdOrderBySentAtDesc(receiverId);
        return messages.stream().map(this::toDto).collect(Collectors.toList());
    }

    // 보낸 메시지 가져오기
	public List<MessageDto> getSendMessages(Long sendId) {
        List<Message> messages = messageRepository.findBySenderId(sendId);
        return messages.stream().map(this::toDto).collect(Collectors.toList());
	}
    
    // Entity -> DTO 변환
    private MessageDto toDto(Message message) {
        MessageDto dto = new MessageDto();
        dto.setId(message.getId());
        dto.setSenderId(message.getSender().getId());
        dto.setReceiverId(message.getReceiver().getId());
        dto.setContent(message.getContent());
        dto.setSentAt(message.getSentAt());
        dto.setSenderUsername(message.getSender().getUsername());
        dto.setReceiverUsername(message.getReceiver().getUsername());
        return dto;
    }
}