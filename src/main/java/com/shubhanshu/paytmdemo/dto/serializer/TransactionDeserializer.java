package com.shubhanshu.paytmdemo.dto.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shubhanshu.paytmdemo.dto.TransactionLogDTO;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class TransactionDeserializer implements Deserializer<TransactionLogDTO> {
    @Override
    public void configure(Map map, boolean b) {

    }

    @Override
    public TransactionLogDTO deserialize(String s, byte[] bytes) {
        ObjectMapper mapper = new ObjectMapper();
        TransactionLogDTO t = null;
        try {
            t = mapper.readValue(bytes, TransactionLogDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public void close() {

    }
}
