package com.example.sns.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sns.domain.ErrorResponseDto;
import com.example.sns.domain.LoginRequestDto;
import com.example.sns.domain.LoginResponseDto;
import com.example.sns.service.UserService;
import com.example.sns.util.JwtTokenUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {
        log.info("Received login request for user: {}", loginRequest.getUsername());

        try {
            // 사용자 인증
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
            
            log.info("Authentication successful for user: {}", loginRequest.getUsername());

            // 토큰 생성
            String token = jwtTokenUtil.generateToken(loginRequest.getUsername());

            log.info("Generated JWT Token: {}", token);

            // JWT 토큰 반환
            return ResponseEntity.ok(new LoginResponseDto(token));
        } catch (Exception e) {
            log.error("Authentication failed for user: {}. Error: {}", loginRequest.getUsername(), e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponseDto("Authentication failed", e.getMessage()));
        }
    }
}