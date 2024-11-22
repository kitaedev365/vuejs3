package com.example.shop.domain;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderItemDto {
    private Long productId;
    private Integer quantity;
    private BigDecimal price;
}