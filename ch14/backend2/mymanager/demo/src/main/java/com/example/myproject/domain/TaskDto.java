package com.example.myproject.domain;

import lombok.Data;

@Data
public class TaskDto {
    private Long id;
    private String name;
    private String description;
    private String status;
    private Long projectId;
    private String dueDate;
}