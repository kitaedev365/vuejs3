package com.example.shop.controller;

import com.example.shop.service.UserService;
import com.example.shop.util.JwtTokenUtil;
import com.example.shop.domain.LoginRequestDto;
import com.example.shop.domain.LoginResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth2")
public class AuthController2 {

    @Autowired
    UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequest) {

        // 사용자 인증
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        // 토큰 생성
        String token = jwtTokenUtil.generateToken(loginRequest.getUsername());

        // JWT 토큰 반환
        return new LoginResponseDto(token);
    }
}