package com.shubhanshu.paytmdemo.controller;

import com.shubhanshu.paytmdemo.dto.*;
import com.shubhanshu.paytmdemo.model.PaytmUser;
import com.shubhanshu.paytmdemo.model.TransactionLog;
import com.shubhanshu.paytmdemo.model.Wallet;
import com.shubhanshu.paytmdemo.service.UserService;
import com.shubhanshu.paytmdemo.service.WalletService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import javax.ws.rs.POST;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    private WalletService walletService;
    private UserService userService;

    public WalletController(WalletService walletService, UserService userService) {
        this.walletService = walletService;
        this.userService = userService;
    }

    @POST
    @RequestMapping("/create")
    public DTO createWallet(@RequestParam Long phoneNumber){
        if(phoneNumber == null) {
            return new ErrorDTO("Phone Number cannot be null");
        }

        try {
            Wallet wallet = walletService.createWallet(phoneNumber);
            return new WalletDTO(wallet.getId(), wallet.getMoney());
        } catch (Exception e) {
            return new ErrorDTO(e.getMessage(), e.toString());
        }

    }

    @POST
    @RequestMapping("/addMoney")
    public DTO addMoney(@RequestParam Long phoneNumber, @RequestParam Long amount) {
        if(phoneNumber == null || amount == null){
            return new ErrorDTO("Phone Nummber/ Amount can't be null");
        }
        try {
            walletService.addMoney(phoneNumber, amount);
            return new SuccessDTO("Money added in wallet");
        } catch (Exception e) {
            return new ErrorDTO(e.getMessage(), e.toString());
        }
    }

    @POST
    @RequestMapping("/sendMoney")
    public DTO sendMoney(@RequestParam Long fromPhoneNumber, @RequestParam Long toPhoneNumber, @RequestParam Long amount) {
        if(fromPhoneNumber == null || toPhoneNumber == null || amount == null){
            return new ErrorDTO("(Reciever/ Sender)Phone Nummber/ Amount can't be null");
        }
        try {
            TransactionLog transactionLog = walletService.sendMoney(fromPhoneNumber, toPhoneNumber, amount);
            return new TransactionLogDTO(transactionLog.getId(), transactionLog.getFromUser().getPhoneNumber(), transactionLog.getToUser().getPhoneNumber(), transactionLog.getAmountSent(), transactionLog.getCreateDateTime());
        } catch (Exception e) {
            return new ErrorDTO(e.getMessage(), e.toString());
        }
    }

    @GET
    @RequestMapping("/checkBalance")
    public Long checkBalance(@RequestParam Long phoneNumber){
        if(phoneNumber == null) {
            return null;
        }

        PaytmUser user = userService.findByPhoneNumber(phoneNumber);
        Wallet userWallet = user.getWallet();
        if(userWallet == null) {
            return null;
        }
        return userWallet.getMoney();
    }
}
