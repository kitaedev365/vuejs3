package com.example.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseInfoDTO {
    private Long id;
    private Long courseId;
    private String topic;
    private String details;
    private String mediafile;
    private Integer durationHours;
}