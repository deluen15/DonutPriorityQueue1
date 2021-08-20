package com.demo.donutpriorityqueue.endpoint;

import com.demo.donutpriorityqueue.model.OrderModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<OrderModel, Long> {
    void delete(long id);
}
