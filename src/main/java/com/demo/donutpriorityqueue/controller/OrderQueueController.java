package com.demo.donutpriorityqueue.controller;

import com.demo.donutpriorityqueue.Dto.OrderPositionAndWaitTime;
import com.demo.donutpriorityqueue.Dto.OrderWithWaitTime;
import com.demo.donutpriorityqueue.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/order-queue")
public class OrderQueueController {

    final OrderService service;

    public OrderQueueController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/{clientId}")
    public OrderPositionAndWaitTime getOrderPositionAndWaitTime(@PathVariable long clientId) {
        return this.service.getPositionInQueueAndWaitTime(clientId);
    }

    @GetMapping()
    public List<OrderWithWaitTime> getOrderPositionAndWaitTime() {
        return this.service.getOrdersWithTheirWaitTime();
    }

}
