package com.demo.donutpriorityqueue.Dto;

import com.demo.donutpriorityqueue.model.OrderModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto implements Comparable<OrderModel>, Serializable
{
    protected long clientId;
    protected int quantity;


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        OrderModel order = (OrderModel) obj;
        return order.getId() == clientId &&
                Objects.equals(clientId, order.getId());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getQuantity(), quantity);
    }

    @Override
    public int compareTo(OrderModel order) {
        if (this.getQuantity() > order.getQuantity()) {
            return 1;
        } else if (this.getQuantity() < order.getQuantity()) {
            return -1;
        } else {
            return 0;
        }
    }
}
