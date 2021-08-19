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
    public ResponseEntity.BodyBuilder addItems(@RequestBody OrderDto orderRequest){
        if (this.service.hasOrderInProgress(orderRequest.getClientId()))
            return ResponseEntity.badRequest();
        service.addItems(orderRequest);
        return ResponseEntity.ok();
    }

    @GetMapping("/{id}")
    public ResponseEntity.BodyBuilder checkPosition(@PathVariable long id){
        if (id == 0){
            return ResponseEntity.badRequest();
        }
        service.checkQueuePosition(id);
        return ResponseEntity.status(HttpStatus.valueOf("Position is :" + checkPosition(id)));
    }

    @GetMapping
    public List<OrderModel> findAll(@RequestBody OrderDto order){
        if(order.getClientId() == 0){
            ResponseEntity.badRequest();
        }
        return service.findAll();
    }
    @GetMapping
    public List<OrderModel> getDelivery(OrderDto orderDto){

        return null;
    }
    @PostMapping("/{id}")
    public void cancelOrder(OrderDto orderDto){
        if(orderDto.getClientId() == 0){
            ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
        service.cancelOrder(orderDto);
        ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE);
    }
}
