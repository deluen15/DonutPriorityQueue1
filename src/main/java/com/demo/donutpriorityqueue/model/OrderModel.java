package com.demo.donutpriorityqueue.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel implements Comparable<OrderModel> {

    protected long id;
    protected Client client;
    protected int quantity;
    protected boolean isCanceled;
    protected boolean isComplete;
    protected LocalTime registeredInTheQueueAt;

    @Override
    public int compareTo(OrderModel o) {
        if (this.client.isPremium() && o.client.isPremium())
            return  this.registeredInTheQueueAt.compareTo(o.registeredInTheQueueAt);
        if (this.client.isPremium())
            return 1;
        if (o.client.isPremium())
            return -1;
        return  this.registeredInTheQueueAt.compareTo(o.registeredInTheQueueAt);
    }

    public boolean equals(Object obj) {
        OrderModel om = (OrderModel) obj;
        return this.id == om.getId();
    }

}
