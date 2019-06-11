package com.shubhanshu.paytmdemo.service;

import com.shubhanshu.paytmdemo.model.PaytmUser;
import com.shubhanshu.paytmdemo.model.TransactionLog;
import com.shubhanshu.paytmdemo.model.Wallet;
import com.shubhanshu.paytmdemo.repository.PaytmUserRepository;
import com.shubhanshu.paytmdemo.repository.TransactionRepository;
import com.shubhanshu.paytmdemo.repository.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;
    private WalletRepository walletRepository;

    public TransactionService(TransactionRepository transactionRepository, WalletRepository walletRepository) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
    }

    public TransactionLog createTransaction(PaytmUser fromUser, PaytmUser toUser, Long amount) {
        TransactionLog t = new TransactionLog();
        t.setFromUser(fromUser);
        t.setToUser(toUser);
        t.setAmountSent(amount);
        transactionRepository.save(t);

        Wallet fromUserWallet = fromUser.getWallet();
        Wallet toUserWallet = toUser.getWallet();

        if(fromUserWallet.getMoney() < amount) {
            throw new IllegalArgumentException("Not enough money in wallet");
        }

        fromUserWallet.setMoney(fromUserWallet.getMoney()-amount);
        toUserWallet.setMoney(toUserWallet.getMoney()+amount);
        walletRepository.save(fromUserWallet);
        walletRepository.save(toUserWallet);
        return t;
    }
}
