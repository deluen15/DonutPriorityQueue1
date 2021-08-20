package com.demo.donutpriorityqueue.Dto;

import com.demo.donutpriorityqueue.model.OrderModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
public class OrderWithWaitTime {

    protected LocalTime approximateWaitTime;
    protected OrderModel orderModel;
}
