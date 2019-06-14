package com.shubhanshu.paytmdemo.mq;

import com.shubhanshu.paytmdemo.dto.TransactionLogDTO;
import com.shubhanshu.paytmdemo.model.TransactionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final String TOPIC = "test";

    @Autowired
    private KafkaTemplate<String, TransactionLogDTO> kafkaTemplate;

    public void sendMessage(TransactionLogDTO t){
        this.kafkaTemplate.send(TOPIC,t);
    }
}
