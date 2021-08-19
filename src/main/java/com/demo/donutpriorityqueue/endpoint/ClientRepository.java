package com.demo.donutpriorityqueue.endpoint;

import com.demo.donutpriorityqueue.model.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {
}
