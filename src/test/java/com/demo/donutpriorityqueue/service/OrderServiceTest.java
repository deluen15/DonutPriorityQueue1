package com.demo.donutpriorityqueue.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.demo.donutpriorityqueue.endpoint.ClientRepository;
import com.demo.donutpriorityqueue.endpoint.OrderRepository;
import com.demo.donutpriorityqueue.model.OrderModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OrderService.class})
@ExtendWith(SpringExtension.class)
public class OrderServiceTest {
    @MockBean
    private ClientRepository clientRepository;

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Test
    public void testCheckQueuePosition() {
        when(this.orderRepository.findAll()).thenReturn((Iterable<OrderModel>) mock(Iterable.class));
        this.orderService.checkQueuePosition(123L);
        verify(this.orderRepository).findAll();
    }
}

