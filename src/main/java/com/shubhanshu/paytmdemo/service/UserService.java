package com.shubhanshu.paytmdemo.service;

import com.shubhanshu.LogicConstants.UserType;
import com.shubhanshu.exceptions.PersistenceException;
import com.shubhanshu.paytmdemo.model.PaytmUser;
import com.shubhanshu.paytmdemo.repository.PaytmUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private PaytmUserRepository user;

    public UserService(PaytmUserRepository user) {
        this.user = user;
    }

    public PaytmUser createPaytmUser(String firstName, String lastName, UserType userType, Long phoneNumber) throws Exception {
        PaytmUser userByPhoneNumber = user.findByPhoneNumber(phoneNumber);
        if (userByPhoneNumber == null) {
            try {
                PaytmUser newUser = new PaytmUser(firstName, lastName, userType, phoneNumber);
                user.save(newUser);
                return newUser;
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        } else {
            throw new PersistenceException("User Already Exists");
        }
    }

    public PaytmUser findByPhoneNumber(Long phoneNumber) {
        if(phoneNumber == null){
            return null;
        }

        return user.findByPhoneNumber(phoneNumber);
    }
}
