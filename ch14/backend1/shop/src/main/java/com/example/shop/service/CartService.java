package com.example.shop.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.shop.domain.CartDto;
import com.example.shop.domain.ProductDto;
import com.example.shop.domain.RequestCartDto;
import com.example.shop.domain.ResponseCartDto;
import com.example.shop.domain.UserDto;
import com.example.shop.entity.Cart;
import com.example.shop.entity.Product;
import com.example.shop.entity.User;
import com.example.shop.repository.CartRepository;
import com.example.shop.repository.ProductRepository;
import com.example.shop.repository.UserRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    // 장바구니에 상품 추가
    @Transactional
    public void addToCart(RequestCartDto cartDto) throws Exception {
        // 사용자가 존재하는지 확인
        User user = userRepository.findByUsername(cartDto.getUsername())
                .orElseThrow(() -> new Exception("User not found"));

        // 상품이 존재하는지 확인
        Product product = productRepository.findById(cartDto.getId())
                .orElseThrow(() -> new Exception("Product not found"));

        // 장바구니에 상품이 이미 존재하는지 확인
        Cart existingCart = cartRepository.findByProductAndUser(product, user);
        if (existingCart != null) {
            throw new Exception("Product already exists in cart");
        }

        // 새로운 장바구니 항목 추가
        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setUser(user);
        cartRepository.save(cart);
    }

    // 로그인한 사용자의 장바구니 목록 조회
    public List<ResponseCartDto> getUserCart(String username) throws Exception {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new Exception("User not found"));

        List<Cart> carts = cartRepository.findByUser(user);
        return carts.stream()
                .map(cart -> {
                	ResponseCartDto cartDto = new ResponseCartDto();
                    cartDto.setId(cart.getId());
                    cartDto.setProduct(cart.getProduct()); // ProductDto로 변환
                    cartDto.setUser(cart.getUser()); // UserDto로 변환
                    return cartDto;
                })
                .collect(Collectors.toList());
    }

    // 장바구니 항목 삭제
    @Transactional
    public void deleteFromCart(Long cartId) throws Exception {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new Exception("Cart item not found"));
        cartRepository.delete(cart);
    }
}