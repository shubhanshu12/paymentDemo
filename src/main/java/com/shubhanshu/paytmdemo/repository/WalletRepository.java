package com.shubhanshu.paytmdemo.repository;

import com.shubhanshu.paytmdemo.model.Wallet;
import org.springframework.data.repository.CrudRepository;

public interface WalletRepository extends CrudRepository<Wallet, Long> {
}
