package com.demo.donutpriorityqueue.service;

import com.demo.donutpriorityqueue.Dto.OrderDto;
import com.demo.donutpriorityqueue.endpoint.ClientRepository;
import com.demo.donutpriorityqueue.endpoint.OrderRepository;
import com.demo.donutpriorityqueue.model.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    public long checkQueuePosition(long id){
        Iterator iterator = priorityQueue.iterator();
        int counter = 1;
        while (iterator.hasNext()){
            if (counter == id){
                iterator.remove();
            }
            counter++;
        }
        return repository.count();
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

    public ResponseEntity.BodyBuilder getDelivery(OrderDto orderDto){
       if (this.hasOrderInProgress(orderDto.getClientId())){
           if(orderDto.getQuantity() < 50){
               return null;
           }
           else if (orderDto.getQuantity() > 50) {
               return null;
           }
       }else
            new ResponseEntity("Delivery Information", HttpStatus.CREATED);

       return null;
            //if order != 0
//            //if orders < 50 return orders;
//             //if > 50 than 50
//            //return first 50
//        //else
//        //return message
//        //Marrim quene dhe i bejme dequeue 50 orders
    }

    public void cancelOrder(long id){
        repository.delete(id);
    }

    public boolean hasOrderInProgress(long clientId) {
        return this.priorityQueue.stream().anyMatch((order) -> order.getClient().getId() == clientId);
    }
}
