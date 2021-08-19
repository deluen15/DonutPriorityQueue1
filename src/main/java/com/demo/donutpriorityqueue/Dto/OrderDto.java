package com.demo.donutpriorityqueue.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto
//        implements Comparable<OrderModel>, Serializable
{
    protected long clientId;
    protected int quantity;

}
