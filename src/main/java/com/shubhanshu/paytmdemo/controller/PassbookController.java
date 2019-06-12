package com.shubhanshu.paytmdemo.controller;

import com.shubhanshu.paytmdemo.dto.TransactionLogDTO;
import com.shubhanshu.paytmdemo.model.PaytmUser;
import com.shubhanshu.paytmdemo.model.TransactionLog;
import com.shubhanshu.paytmdemo.model.Wallet;
import com.shubhanshu.paytmdemo.service.TransactionService;
import com.shubhanshu.paytmdemo.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/passbook")
public class PassbookController {

    private TransactionService transactionService;
    private UserService userService;

    public PassbookController(TransactionService transactionService, UserService userService) {
        this.transactionService = transactionService;
        this.userService = userService;
    }

    @RequestMapping(value = "/transactions", method = RequestMethod.GET)
    public List<TransactionLogDTO> getPassbook(@RequestParam Long phoneNumber) {
        PaytmUser user = userService.findByPhoneNumber(phoneNumber);
        if (user == null) return null;

        Wallet userWallet = user.getWallet();
        if (userWallet == null) return null;

        Set<TransactionLog> allTransactions = transactionService.getAllTransactions(phoneNumber);
        System.out.println(allTransactions.size());
        List<TransactionLogDTO> transactionLogDTOSet = new ArrayList<>();
        for (TransactionLog tr : allTransactions) {
            transactionLogDTOSet.add(new TransactionLogDTO(tr.getId(), tr.getFromUser().getPhoneNumber(), tr.getToUser().getPhoneNumber(), tr.getAmountSent(), tr.getCreateDateTime(), tr.getStatus(), tr.getTransactionType()));
        }
//        PassbookDTO passbookDTO = new PassbookDTO();
//        passbookDTO.setTransactionLogSet(transactionLogDTOSet);

        return transactionLogDTOSet;
    }

}
