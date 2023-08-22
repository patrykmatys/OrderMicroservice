package org.thesis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thesis.models.OrderHistory;
import org.thesis.services.order.OrderService;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/add/{user}")
    public ResponseEntity<OrderHistory> addOrder(@PathVariable String user) {
        return ResponseEntity.ok(orderService.saveOrder(user));
    }

    @GetMapping("/{user}")
    public ResponseEntity<OrderHistory> getOrders(@PathVariable String user) {
        return ResponseEntity.ok(orderService.getOrderHistory(user));
    }

}