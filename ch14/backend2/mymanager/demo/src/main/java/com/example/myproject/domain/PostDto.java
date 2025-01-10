package com.example.myproject.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PostDto {
    private Long id;
    private String title;
    private String content;
    private int position;
    private Long boardId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}