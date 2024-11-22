package com.example.sns.domain;

import lombok.Data;

@Data
public class LoginRequestDto {
	private String username;
	private String password;
}
