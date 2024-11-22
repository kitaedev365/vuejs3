package com.example.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shop.entity.Order;
import com.example.shop.entity.OrderItem;
import com.example.shop.repository.OrderItemRepository;
import com.example.shop.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public Order createOrder(Order order, List<OrderItem> orderItems) {
        Order savedOrder = orderRepository.save(order);
        orderItems.forEach(item -> {
            item.setOrderId(savedOrder.getId());
            orderItemRepository.save(item);
        });
        return savedOrder;
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }
}
