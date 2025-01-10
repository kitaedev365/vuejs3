package com.example.cms.domain;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String username;
    private String password;
}