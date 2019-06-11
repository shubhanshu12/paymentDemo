package com.shubhanshu.paytmdemo.service;

import com.shubhanshu.exceptions.PersistenceException;
import com.shubhanshu.paytmdemo.dto.PaytmUserDTO;
import com.shubhanshu.paytmdemo.dto.TransactionLogDTO;
import com.shubhanshu.paytmdemo.model.PaytmUser;
import com.shubhanshu.paytmdemo.model.TransactionLog;
import com.shubhanshu.paytmdemo.model.Wallet;
import com.shubhanshu.paytmdemo.repository.PaytmUserRepository;
import com.shubhanshu.paytmdemo.repository.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private WalletRepository walletRepository;
    private PaytmUserRepository paytmUserRepository;
    private TransactionService transactionService;

//    @Value("${wallet.maxAddAmountLimit}")
    private Long maxAddAmountLimit = 20000L;

//    @Value("${wallet.minAddAmountLimit}")
    private Long minAddAmountLimit = 1L;

//    @Value("${wallet.maxAddAmountLimit}")
    private Long maxTransferAmountLimit = 10000L;

    //    @Value("${wallet.minAddAmountLimit}")
    private Long minTransferAmountLimit = 1L;

    public WalletService(WalletRepository walletRepository, PaytmUserRepository paytmUserRepository, TransactionService transactionService) {
        this.walletRepository = walletRepository;
        this.paytmUserRepository = paytmUserRepository;
        this.transactionService = transactionService;
    }

    public Wallet createWallet(Long phoneNumber) throws PersistenceException {
        if(phoneNumber == null) {
            return null;
        }

        PaytmUser user = paytmUserRepository.findByPhoneNumber(phoneNumber);
        if(user == null) {
            throw new IllegalArgumentException("User doesn't exist");
        }
        Wallet userWallet = user.getWallet();
        if(userWallet !=  null) {
            throw new PersistenceException("Wallet for this user already exists");
        }

        try {
            Wallet wallet = new Wallet();
            wallet.setMoney(0L);
            walletRepository.save(wallet);
            user.setWallet(wallet);
            paytmUserRepository.save(user);
            return wallet;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void addMoney(Long phoneNumber, Long amount) {

        PaytmUser user = paytmUserRepository.findByPhoneNumber(phoneNumber);
        if(user == null) {
            throw new IllegalArgumentException("User doesn't exist");
        }

        if(amount < minAddAmountLimit){
            throw new IllegalArgumentException("Add more money!! Min. amount that can be added is: " + minAddAmountLimit);
        }
        if(amount > maxAddAmountLimit){
            throw new IllegalArgumentException("Maximum allowed limit is: " + maxAddAmountLimit);
        }
        Wallet userWallet = user.getWallet();
        if(userWallet == null) {
            throw new IllegalArgumentException("User has no wallet");
        }
        try {
            userWallet.setMoney(amount);
            walletRepository.save(userWallet);
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public TransactionLog sendMoney(Long fromPhoneNumber, Long toPhoneNumber, Long amount) {
        if (amount < minTransferAmountLimit) {
            throw new IllegalArgumentException("Min transfer amount: " + minTransferAmountLimit);
        }

        if (amount > maxTransferAmountLimit) {
            throw new IllegalArgumentException("Max transfer amount: " + maxTransferAmountLimit);
        }
        PaytmUser fromUser = paytmUserRepository.findByPhoneNumber(fromPhoneNumber);
        PaytmUser toUser = paytmUserRepository.findByPhoneNumber(toPhoneNumber);

        if (fromUser == null) {
            throw new IllegalArgumentException("User: " + fromPhoneNumber + " does not exits");
        }

        if (toUser == null) {
            throw new IllegalArgumentException("User: " + fromPhoneNumber + " does not exits");
        }

        Wallet fromUserWallet = fromUser.getWallet();
        Wallet toUserWallet = toUser.getWallet();

        if (fromUserWallet == null || toUserWallet == null) {
            throw new IllegalArgumentException("One of the user doesn't have a wallet");
        }

        TransactionLog transaction = transactionService.createTransaction(fromUser, toUser, amount);
        return transaction;
    }
}
