package com.example.shop.domain;

import com.example.shop.entity.Product;
import com.example.shop.entity.User;

public class ResponseCartDto {
    private Long id;
    private Product product;
    private User user;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
