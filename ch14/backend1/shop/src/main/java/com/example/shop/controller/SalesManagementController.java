package com.example.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop.entity.Order;
import com.example.shop.service.SalesManagementService;

@RestController
@RequestMapping("/api/sales")
public class SalesManagementController {

    @Autowired
    private SalesManagementService salesManagementService;

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return salesManagementService.findAllOrders();
    }

    @PutMapping("/orders/{orderId}/status")
    public Order updateOrderStatus(@PathVariable Long orderId, @RequestParam String status) {
        return salesManagementService.updateOrderStatus(orderId, status);
    }
}
