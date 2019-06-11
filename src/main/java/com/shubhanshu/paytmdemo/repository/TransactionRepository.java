package com.shubhanshu.paytmdemo.repository;

import com.shubhanshu.paytmdemo.model.TransactionLog;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<TransactionLog, Long> {
}
