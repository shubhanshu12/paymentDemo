package com.shubhanshu.paytmdemo.mq;

import com.shubhanshu.paytmdemo.dto.TransactionLogDTO;
import com.shubhanshu.paytmdemo.model.TransactionLog;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @KafkaListener(topics = "test")
    public void consume(TransactionLogDTO t){
        System.out.println("Consuming transaction: " + t);
    }
}
