package com.example.shop.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.shop.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDto {
    private Long id;            // 상품 ID
    private String name;        // 상품명
    private String description; // 상품 설명
    private BigDecimal price;   // 상품 가격
    private int stock;          // 상품 재고
    private LocalDateTime createdAt; // 상품 등록일
    private String img;
    private List<CartDto> carts; // 상품과 관련된 장바구니 목록 (CartDto 포함)

    // Product 엔티티로부터 DTO 생성
    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.createdAt = product.getCreatedAt();
        this.img = product.getImg();
    }
}
