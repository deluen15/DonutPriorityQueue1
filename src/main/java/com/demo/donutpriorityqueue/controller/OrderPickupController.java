package com.demo.donutpriorityqueue.controller;

import com.demo.donutpriorityqueue.model.OrderModel;
import com.demo.donutpriorityqueue.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/pickup-order")
public class OrderPickupController {

    final OrderService orderService;

    public OrderPickupController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/")
    public List<OrderModel> getPickupOrders() {
        return this.orderService.getNextDelivery();
    }
}
