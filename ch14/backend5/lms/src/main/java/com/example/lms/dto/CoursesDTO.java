package com.example.lms.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoursesDTO {
    private Long id;
    private String title;
    private String description;
    private String lector;
    private String startDate;
    private String endDate;
    private Long instructorId;
}