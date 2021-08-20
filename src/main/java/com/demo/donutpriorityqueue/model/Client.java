package com.demo.donutpriorityqueue.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    protected long id;


    public boolean isPremium() {
        return this.id < 1000;
    }

}
