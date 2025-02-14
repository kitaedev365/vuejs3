package com.example.sns.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long id;
    private Long userId;
    private String username;
    private String content;
    private String mediaUrl;
    private String mediaType;
    private java.time.LocalDateTime createdAt;
}
