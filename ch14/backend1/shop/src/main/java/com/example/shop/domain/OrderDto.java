package com.example.shop.domain;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class OrderDto {
    private Long userId;
    private BigDecimal totalAmount;
    private List<OrderItemDto> orderItems; // 주문 항목 리스트
}