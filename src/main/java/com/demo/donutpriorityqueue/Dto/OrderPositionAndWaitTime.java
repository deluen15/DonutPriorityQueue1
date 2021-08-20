package com.demo.donutpriorityqueue.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@NoArgsConstructor
@Data
public class OrderPositionAndWaitTime {

    protected LocalTime timeTillReady;
    protected int queuePosition;
}
