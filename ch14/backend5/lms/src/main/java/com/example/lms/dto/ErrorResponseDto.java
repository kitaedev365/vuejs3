package com.example.lms.dto;

import lombok.Data;

@Data
public class ErrorResponseDto {
    private String error;
    private String message;

    public ErrorResponseDto(String error, String message) {
        this.error = error;
        this.message = message;
    }
}