package com.example.myproject.domain;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String password;
    private String role;
    private String email; // 반드시 추가
}