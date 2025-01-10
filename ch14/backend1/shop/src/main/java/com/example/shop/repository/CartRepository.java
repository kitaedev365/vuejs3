package com.example.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shop.entity.Cart;
import com.example.shop.entity.Product;
import com.example.shop.entity.User;

public interface CartRepository extends JpaRepository<Cart, Long> {

    // 사용자가 장바구니에 담은 상품을 조회
    List<Cart> findByUser(User user);

    // 특정 사용자가 장바구니에 담은 특정 상품을 조회
    Cart findByProductAndUser(Product product, User user);
}