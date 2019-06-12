package com.shubhanshu.paytmdemo.repository;

import com.shubhanshu.paytmdemo.model.PaytmUser;
import com.shubhanshu.paytmdemo.model.TransactionLog;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface TransactionRepository extends CrudRepository<TransactionLog, Long> {
    Set<TransactionLog> findByFromUser(PaytmUser fromUser);

    Set<TransactionLog> findByToUser(PaytmUser fromUser);
}
