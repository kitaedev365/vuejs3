package com.example.cms.domain;

import lombok.Data;

@Data
public class ContentDto {
    private String title;
    private String description;
    private String content;
    private Long authorId;
}