package com.example.lms.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionsDTO {
    private Long id;
    private Long courseId;
    private String courseTitle;
    private String questionText;
    private String answer;
}