package com.shubhanshu.paytmdemo.repository;

import com.shubhanshu.paytmdemo.model.PaytmUser;
import org.springframework.data.repository.CrudRepository;

public interface PaytmUserRepository extends CrudRepository<PaytmUser, Long>, PaytmUserRepoCustom {

    PaytmUser findByPhoneNumber(Long phoneNumber);
}
