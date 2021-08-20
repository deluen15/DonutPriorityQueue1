package com.demo.donutpriorityqueue.service;

import com.demo.donutpriorityqueue.Dto.OrderDto;
import com.demo.donutpriorityqueue.Dto.OrderWithWaitTime;
import com.demo.donutpriorityqueue.model.Client;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ContextConfiguration(classes = {OrderService.class})
@ExtendWith(SpringExtension.class)
public class OrderServiceTest {

    private final OrderService orderService;

    public OrderServiceTest() {
        this.orderService = new OrderService();
        Client testClient1 = new Client();
        testClient1.setId(1);

        Client testClient2 = new Client();
        testClient2.setId(1001);

        this.orderService.clientRepository.add(testClient1);
        this.orderService.clientRepository.add(testClient2);
    }


    @Test
    public void testAddItem() {
        OrderDto orderDto = new OrderDto();
        orderDto.setClientId(1001);
        orderDto.setQuantity(2);
        this.orderService.addItems(orderDto);
        assertFalse(this.orderService.priorityQueue.isEmpty());
    }

    @Test
    public void testCancelOrder() {
        // Arrange
        OrderDto orderDto = new OrderDto();

        // Act
        orderDto.setClientId(1001);
        orderDto.setQuantity(2);
        this.orderService.addItems(orderDto);

        orderService.cancelOrder(orderDto.getClientId());
        // Assert
        assertTrue(this.orderService.priorityQueue.isEmpty());
        assertTrue(this.orderService.orderRepository.isEmpty());
    }

    @Test
    public void testGetOrdersWithTheirWaitTime() {
        // Arrange and Act
        List<OrderWithWaitTime> actualOrdersWithTheirWaitTime = this.orderService.getOrdersWithTheirWaitTime();

        // Assert
        assertTrue(actualOrdersWithTheirWaitTime.isEmpty());
    }

    @Test
    public void testHasOrderInProgress() {
        // Arrange
        long clientId = 123L;

        // Act
        boolean actualHasOrderInProgressResult = this.orderService.hasOrderInProgress(clientId);

        // Assert
        assertFalse(actualHasOrderInProgressResult);
    }

}

