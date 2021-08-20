package com.demo.donutpriorityqueue.service;

import com.demo.donutpriorityqueue.Dto.OrderDto;
import com.demo.donutpriorityqueue.endpoint.ClientRepository;
import com.demo.donutpriorityqueue.endpoint.OrderRepository;
import com.demo.donutpriorityqueue.model.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

@Service
public class OrderService {

    final OrderRepository repository;
    final ClientRepository clientRepository;
    final PriorityQueue<OrderModel> priorityQueue;

    @Autowired
    public OrderService(OrderRepository repository, ClientRepository clientRepository) {
        this.repository = repository;
        this.clientRepository = clientRepository;
        this.priorityQueue = new PriorityQueue<>();
    }
    public void addItems(OrderDto orderDto){
        OrderModel order = new OrderModel();
        order.setQuantity(orderDto.getQuantity());
        order.setClient(clientRepository.findById(orderDto.getClientId()).get());
        repository.save(order);
    }
    public Iterable<OrderModel> checkQueuePosition(long id){
        Iterator iterator = priorityQueue.iterator();
        int counter = 1;
        while (iterator.hasNext()){
            if (counter == id){
                iterator.remove();
            }
            counter++;
        }
        return repository.findAll();
    }
    public List<OrderModel> findAll() {
        OrderModel order = new OrderModel();
        priorityQueue.add(order);
        Iterator<OrderModel> iterator = priorityQueue.iterator();
        while (iterator.hasNext()){
            List<OrderModel> next = (List<OrderModel>) iterator.next();
            return next;
        }
        return (List<OrderModel>) repository.findAll();
    }

    public List<OrderDto> getDelivery(OrderDto orderDto){
       OrderModel order = new OrderModel();
                Arrays.asList(orderDto)
                        .stream()
                        .limit(50)
                        .collect(Collectors.toList());
            //if order != 0
//            //if orders < 50 return orders;
//             //if > 50 than 50
//            //return first 50
//        //else
//        //return message
//        //Marrim quene dhe i bejme dequeue 50 orders
           return null;
    }

    public void cancelOrder(OrderDto orderDto){
        OrderModel order = new OrderModel();
        order.setClient(clientRepository.findById(orderDto.getClientId()).get());
        repository.delete(order);
    }

    public boolean hasOrderInProgress(long clientId) {
        return this.priorityQueue.stream().anyMatch((order) -> order.getClient().getId() == clientId);
    }
}
