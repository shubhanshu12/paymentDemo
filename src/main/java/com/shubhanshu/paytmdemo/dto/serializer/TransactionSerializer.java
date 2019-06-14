package com.shubhanshu.paytmdemo.dto.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shubhanshu.paytmdemo.dto.TransactionLogDTO;
import com.shubhanshu.paytmdemo.model.TransactionLog;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class TransactionSerializer implements Serializer<TransactionLogDTO> {


    @Override
    public void configure(Map map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, TransactionLogDTO o) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsString(o).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;
    }

    @Override
    public void close() {

    }
}
