package com.demo.donutpriorityqueue.controller;

import com.demo.donutpriorityqueue.Dto.OrderDto;
import com.demo.donutpriorityqueue.model.OrderModel;
import com.demo.donutpriorityqueue.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/order")
public class OrderController {

    final OrderService service;
    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity.BodyBuilder addItem(@RequestBody OrderDto orderRequest){
        if (this.service.hasOrderInProgress(orderRequest.getClientId()) || orderRequest.getQuantity() > 50)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST);
        service.addItems(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED);
    }
/*
    @GetMapping("/{id}")
    public ResponseEntity.BodyBuilder checkPosition(@PathVariable long id){
        if (id == 0){
            return ResponseEntity.badRequest();
        }
        service.checkQueuePosition(id);
        return new ResponseEntity("Position is :", HttpStatus.OK);
    }

    @GetMapping
    public List<OrderModel> findAll(@RequestBody OrderDto order){
        if(order.getClientId() == 0){
            ResponseEntity.badRequest();
        }
        return service.findAll();
    }
    @GetMapping
    public ResponseEntity.BodyBuilder getDelivery(OrderDto orderDto){
        if (orderDto.getQuantity() != 0) {
            return service.getDelivery(orderDto);
        }
        return (ResponseEntity.BodyBuilder) new ResponseEntity("Bla bla", HttpStatus.CREATED);
    }
    @PostMapping("/{id}")*/
    public void cancelOrder(@PathVariable long id){
        OrderDto orderDto = new OrderDto();
        if(orderDto.getClientId() == 0){
            ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
        service.cancelOrder(id);
        new ResponseEntity("Order is canceled", HttpStatus.OK );
    }
}
