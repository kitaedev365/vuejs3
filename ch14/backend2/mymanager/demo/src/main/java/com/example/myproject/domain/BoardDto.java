package com.example.myproject.domain;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class BoardDto {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private List<PostDto> posts;
}
