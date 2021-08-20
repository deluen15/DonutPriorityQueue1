package com.demo.donutpriorityqueue.service;

import com.demo.donutpriorityqueue.Dto.OrderDto;
import com.demo.donutpriorityqueue.Dto.OrderPositionAndWaitTime;
import com.demo.donutpriorityqueue.Dto.OrderWithWaitTime;
import com.demo.donutpriorityqueue.model.Client;
import com.demo.donutpriorityqueue.model.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class OrderService {

    final List<OrderModel> orderRepository;
    final List<Client> clientRepository;
    final PriorityQueue<OrderModel> priorityQueue;

    @Autowired
    public OrderService() {
        this.orderRepository = new ArrayList<>();
        this.clientRepository = new ArrayList<>();
        this.priorityQueue = new PriorityQueue<>();
    }

    public void addItems(OrderDto orderDto){
        OrderModel order = new OrderModel();
        order.setQuantity(orderDto.getQuantity());
        order.setClient(clientRepository.stream().filter((client) -> client.getId() == orderDto.getClientId()).findFirst().get());
        orderRepository.add(order);
        this.priorityQueue.add(order);
    }
    public List<OrderModel> getNextDelivery() {
        AtomicInteger counter = new AtomicInteger(0);
        return this.priorityQueue.stream()
                .filter((order) -> counter.addAndGet(order.getQuantity()) <= 50)
                .collect(Collectors.toList());
    }
    public void cancelOrder(long id){
        OrderModel om = new OrderModel();
        om.setId(id);
        this.orderRepository.remove(om);
        this.priorityQueue.remove(om);
    }
    public List<OrderWithWaitTime> getOrdersWithTheirWaitTime() {
        AtomicInteger maxDonutPerWalkMade = new AtomicInteger();
        AtomicInteger temporaryDonut = new AtomicInteger();
        return this.priorityQueue.stream().map((order) -> {
            temporaryDonut.addAndGet(order.getQuantity());
            if (temporaryDonut.get() >= 50) {
                temporaryDonut.set(0);
                maxDonutPerWalkMade.getAndIncrement();
            }
            OrderWithWaitTime orderWithWaitTime = new OrderWithWaitTime();
            orderWithWaitTime.setApproximateWaitTime(LocalTime.now().plusMinutes(maxDonutPerWalkMade.get() * 5L));
            orderWithWaitTime.setOrderModel(order);
            return orderWithWaitTime;
        }).collect(Collectors.toList());
    }
    public OrderPositionAndWaitTime getPositionInQueueAndWaitTime(long clientId) {
        OrderPositionAndWaitTime orderPositionAndWaitTime = new OrderPositionAndWaitTime();
        orderPositionAndWaitTime.setQueuePosition(1);

        int maxDonutPerWalkMade = 0;
        int temporaryDonut = 0;
        ArrayList<OrderModel> tmpOrders = new ArrayList<>();
        for (OrderModel nextOrder : priorityQueue) {
            if (nextOrder.getClient().getId() == clientId) {
                break;
            }
            temporaryDonut += nextOrder.getQuantity();
            if (temporaryDonut >= 50) {
                temporaryDonut = 0;
                maxDonutPerWalkMade++;
            }
            orderPositionAndWaitTime.setQueuePosition(orderPositionAndWaitTime.getQueuePosition() + 1);
            orderPositionAndWaitTime.setTimeTillReady(LocalTime.now().plusMinutes(maxDonutPerWalkMade * 5L));
            tmpOrders.add(nextOrder);
        }
        priorityQueue.addAll(tmpOrders);
        return orderPositionAndWaitTime;
    }

    public boolean hasOrderInProgress(long clientId) {
        return this.priorityQueue.stream().anyMatch((order) -> order.getClient().getId() == clientId);
    }
}
