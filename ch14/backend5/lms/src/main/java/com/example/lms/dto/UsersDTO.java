package com.example.lms.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersDTO {
    private Long id;
    private String username;
    private String email;
    private String name;
    private String role;
}