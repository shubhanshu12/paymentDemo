package com.shubhanshu.paytmdemo.service;

import com.shubhanshu.logicConstants.TransactionStatus;
import com.shubhanshu.logicConstants.TransactionType;
import com.shubhanshu.logicConstants.UserType;
import com.shubhanshu.paytmdemo.model.PaytmUser;
import com.shubhanshu.paytmdemo.model.TransactionLog;
import com.shubhanshu.paytmdemo.model.Wallet;
import com.shubhanshu.paytmdemo.repository.PaytmUserRepository;
import com.shubhanshu.paytmdemo.repository.TransactionRepository;
import com.shubhanshu.paytmdemo.repository.WalletRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;
    private WalletRepository walletRepository;
    private PaytmUserRepository paytmUserRepository;

    public TransactionService(TransactionRepository transactionRepository, WalletRepository walletRepository, PaytmUserRepository paytmUserRepository) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.paytmUserRepository = paytmUserRepository;
    }

    public TransactionLog createTransaction(PaytmUser fromUser, PaytmUser toUser, Long amount) {
        TransactionLog t = new TransactionLog();
        try {
            initTransaction(fromUser, toUser, amount, t);

            Wallet fromUserWallet = fromUser.getWallet();
            Wallet toUserWallet = toUser.getWallet();

            if (fromUser.getUserType() != UserType.BANK && fromUserWallet.getMoney() < amount) {
                throw new IllegalArgumentException("Not enough money in wallet");
            }
            updateWallet(fromUser, amount, fromUserWallet, toUserWallet);
        } catch (Exception e) {
            e.printStackTrace();
            t.setStatus(TransactionStatus.FAILED);
            transactionRepository.save(t);
            throw e;
        }
        t.setStatus(TransactionStatus.SUCCESS);
        transactionRepository.save(t);
        return t;
    }

    @Transactional
    void updateWallet(PaytmUser fromUser, Long amount, Wallet fromUserWallet, Wallet toUserWallet) {
        if (fromUser.getUserType() != UserType.BANK) {
            fromUserWallet.setMoney(fromUserWallet.getMoney() - amount);
            walletRepository.save(fromUserWallet);
        }
        toUserWallet.setMoney(toUserWallet.getMoney() + amount);
        walletRepository.save(toUserWallet);
    }

    @Transactional
    void initTransaction(PaytmUser fromUser, PaytmUser toUser, Long amount, TransactionLog t) {
        t.setFromUser(fromUser);
        t.setToUser(toUser);
        t.setAmountSent(amount);
        t.setTransactionType(getTransactionType(fromUser, toUser));
        transactionRepository.save(t);
    }

    public Set<TransactionLog> getAllTransactions(Long phoneNumber) {
        PaytmUser user = paytmUserRepository.findByPhoneNumber(phoneNumber);
        Set<TransactionLog> tr = new HashSet<>();
        Set<TransactionLog> byFromUser = transactionRepository.findByFromUser(user);
        Set<TransactionLog> byToUser = transactionRepository.findByToUser(user);
//        Set<TransactionLog> fromUserStream = byFromUser.stream().filter(transactionLog -> transactionLog.getStatus() == TransactionStatus.SUCCESS).collect(Collectors.toSet());
//        Set<TransactionLog> toUserStream = byToUser.stream().filter(transactionLog -> transactionLog.getStatus() == TransactionStatus.SUCCESS).collect(Collectors.toSet());
        tr.addAll(byFromUser);
        tr.addAll(byToUser);
        return tr;
    }

    public TransactionType getTransactionType(PaytmUser fromUser, PaytmUser toUser) {
        if (fromUser == null) throw new IllegalArgumentException("fromUser is null");
        if (toUser == null) throw new IllegalArgumentException("toUser is null");

        if (fromUser.getUserType() == UserType.USER) {
            if (toUser.getUserType() == UserType.USER) {
                return TransactionType.P2P;
            } else if (toUser.getUserType() == UserType.MERCHANT) {
                return TransactionType.P2M;
            }
        } else if (fromUser.getUserType() == UserType.BANK) {
            return TransactionType.ADD_MONEY;
        }
        return null;
    }
}
