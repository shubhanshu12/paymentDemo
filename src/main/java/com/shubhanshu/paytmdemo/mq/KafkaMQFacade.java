package com.shubhanshu.paytmdemo.mq;

import com.shubhanshu.paytmdemo.dto.TransactionLogDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaMQFacade {

    private Producer producer;

    public KafkaMQFacade(Producer producer) {
        this.producer = producer;
    }


    @PostMapping(value = "/publish")
    public void publishToKafka(@RequestBody TransactionLogDTO t) {
        System.out.println(t);
        producer.sendMessage(t);
    }
}
