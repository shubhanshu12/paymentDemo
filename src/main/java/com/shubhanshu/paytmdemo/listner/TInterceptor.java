package com.shubhanshu.paytmdemo.listner;

import com.shubhanshu.paytmdemo.dto.TransactionLogDTO;
import com.shubhanshu.paytmdemo.model.TransactionLog;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;


public class TInterceptor extends EmptyInterceptor {


    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        System.out.println("Interceptor called");
        if (entity instanceof TransactionLog) {
            TransactionLog t = (TransactionLog) entity;
//            kafkaPassFacade.publishToKafka("Saving entity");
            TransactionLogDTO tDto = new TransactionLogDTO(t.getId(), t.getFromUser().getPhoneNumber(), t.getToUser().getPhoneNumber(), t.getAmountSent(), t.getCreateDateTime(), t.getStatus(), t.getTransactionType());
            RestTemplate rest = new RestTemplate();
            rest.postForObject("http://localhost:8080/kafka/publish", tDto, TransactionLogDTO.class);
        }
        return super.onSave(entity, id, state, propertyNames, types);
    }
}
