package com.shubhanshu.paytmdemo.controller;

import com.shubhanshu.paytmdemo.dto.*;
import com.shubhanshu.paytmdemo.model.PaytmUser;
import com.shubhanshu.paytmdemo.model.TransactionLog;
import com.shubhanshu.paytmdemo.model.Wallet;
import com.shubhanshu.paytmdemo.service.UserService;
import com.shubhanshu.paytmdemo.service.WalletService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/wallet")
public class WalletController {

    private WalletService walletService;
    private UserService userService;

    public WalletController(WalletService walletService, UserService userService) {
        this.walletService = walletService;
        this.userService = userService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public DTO createWallet(@RequestParam Long phoneNumber) {
        if (phoneNumber == null) {
            return new ErrorDTO("Phone Number cannot be null");
        }

        try {
            Wallet wallet = walletService.createWallet(phoneNumber);
            return new WalletDTO(wallet.getId(), wallet.getMoney());
        } catch (Exception e) {
            return new ErrorDTO(e.getMessage(), e.toString());
        }

    }

    @RequestMapping(value = "/addMoney", method = RequestMethod.POST)
    public DTO addMoney(@RequestParam Long phoneNumber, @RequestParam Long amount, @RequestParam Long fromBankId) {
        if (phoneNumber == null || amount == null || fromBankId == null) {
            return new ErrorDTO("Phone Nummber/ Amount/ fromBankId can't be null");
        }
        try {
            walletService.addMoney(phoneNumber, amount, fromBankId);
            return new SuccessDTO("Money added in wallet");
        } catch (Exception e) {
            return new ErrorDTO(e.getMessage(), e.toString());
        }
    }

    @RequestMapping(value = "/sendMoney", method = RequestMethod.POST)
    public DTO sendMoney(@RequestParam Long fromPhoneNumber, @RequestParam Long toPhoneNumber, @RequestParam Long amount) {
        if (fromPhoneNumber == null || toPhoneNumber == null || amount == null) {
            return new ErrorDTO("(Reciever/ Sender)Phone Nummber/ Amount can't be null");
        }
        try {
            TransactionLog transactionLog = walletService.sendMoney(fromPhoneNumber, toPhoneNumber, amount);
            return new TransactionLogDTO(transactionLog.getId(), transactionLog.getFromUser().getPhoneNumber(), transactionLog.getToUser().getPhoneNumber(), transactionLog.getAmountSent(), transactionLog.getCreateDateTime(), transactionLog.getStatus(), transactionLog.getTransactionType());
        } catch (Exception e) {
            return new ErrorDTO(e.getMessage(), e.toString());
        }
    }

    @RequestMapping(value = "/checkBalance", method = RequestMethod.GET)
    public Long checkBalance(@RequestParam Long phoneNumber) {
        if (phoneNumber == null) {
            return null;
        }

        PaytmUser user = userService.findByPhoneNumber(phoneNumber);
        Wallet userWallet = user.getWallet();
        if (userWallet == null) {
            return null;
        }
        return userWallet.getMoney();
    }
}
