package com.demo.donutpriorityqueue.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.demo.donutpriorityqueue.Dto.OrderDto;
import com.demo.donutpriorityqueue.Dto.OrderPositionAndWaitTime;
import com.demo.donutpriorityqueue.Dto.OrderWithWaitTime;
import com.demo.donutpriorityqueue.model.Client;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
    public void testConstructor() {
        // Arrange and Act
        OrderService actualOrderService = new OrderService();

        // Assert
        assertTrue(actualOrderService.priorityQueue.isEmpty());
        assertTrue(actualOrderService.orderRepository.isEmpty());
        assertTrue(actualOrderService.clientRepository.isEmpty());
    }

    @Test
    public void testCancelOrder() {
        // Arrange
        long id = 123L;

        // Act
        this.orderService.cancelOrder(id);

        // Assert
        assertTrue(this.orderService.priorityQueue.isEmpty());
        assertTrue(this.orderService.orderRepository.isEmpty());
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

    @Test
    public void testGetOrdersWithTheirWaitTime() {
        // Arrange and Act
        List<OrderWithWaitTime> actualOrdersWithTheirWaitTime = this.orderService.getOrdersWithTheirWaitTime();

        // Assert

        assertTrue(actualOrdersWithTheirWaitTime.isEmpty());
    }

    @Test
    public void testGetPositionInQueueAndWaitTime() {
        // Arrange
        long clientId = 123L;

        // Act
        OrderPositionAndWaitTime actualPositionInQueueAndWaitTime = this.orderService
                .getPositionInQueueAndWaitTime(clientId);

        // Assert
        assertEquals(1, actualPositionInQueueAndWaitTime.getQueuePosition());
        assertTrue(this.orderService.priorityQueue.isEmpty());
    }

    @Test
    public void testGetNextDelivery() {
        // TODO: This test is incomplete.
        //   Reason: No meaningful assertions found.
        //   To help Diffblue Cover to find assertions, please add getters to the
        //   class under test that return fields written by the method under test.
        //   See https://diff.blue/R004

        // Arrange and Act
        this.orderService.getNextDelivery();
    }

}

