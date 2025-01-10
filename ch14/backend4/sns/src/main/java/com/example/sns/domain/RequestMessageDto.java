package com.example.sns.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestMessageDto {
    private Long senderId;
    private Long receiverId;
    private String content;
}
