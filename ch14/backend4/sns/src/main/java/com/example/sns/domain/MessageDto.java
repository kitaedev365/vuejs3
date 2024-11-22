package com.example.sns.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MessageDto {

    private Long id;
    private Long senderId;
    private Long receiverId;
    private String content;
    private LocalDateTime sentAt;

    // Optional: Add sender and receiver usernames if needed for client
    private String senderUsername;
    private String receiverUsername;
}