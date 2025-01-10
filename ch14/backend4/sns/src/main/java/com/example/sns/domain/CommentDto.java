package com.example.sns.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private Long postId;
    private Long userId;
    private String username; // 작성자 이름
    private String content;
    private LocalDateTime createdAt;
}