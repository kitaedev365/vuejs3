package com.example.myproject.domain;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String username;
    private String password;
}