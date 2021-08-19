package com.demo.donutpriorityqueue.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {

    protected long id;
    protected Client client;
    protected int quantity;
    protected boolean isCanceled;
    protected boolean isComplete;

    public int getPriority() {
        return 0;
    }
}
