package com.shubhanshu.paytmdemo.controller;

import com.shubhanshu.LogicConstants.UserType;
import com.shubhanshu.exceptions.PersistenceException;
import com.shubhanshu.paytmdemo.dto.DTO;
import com.shubhanshu.paytmdemo.dto.ErrorDTO;
import com.shubhanshu.paytmdemo.dto.PaytmUserDTO;
import com.shubhanshu.paytmdemo.model.PaytmUser;
import com.shubhanshu.paytmdemo.repository.PaytmUserRepository;
import com.shubhanshu.paytmdemo.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import javax.ws.rs.POST;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @POST
    @RequestMapping("/create")
    public DTO createUser(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String userType, @RequestParam Long phoneNumber) {

        if (firstName == null || lastName == null || phoneNumber == null || userType == null) {
            return new ErrorDTO("First Name/ Last Name/ Phone Number / User type cannot be empty");
        }

        try {
            PaytmUser newUser = userService.createPaytmUser(firstName, lastName, UserType.valueOf(userType), phoneNumber);
            return new PaytmUserDTO(newUser.getId(), newUser.getFirstName(), newUser.getLastName(), newUser.getUserType(), newUser.getPhoneNumber());
        }  catch (PersistenceException e) {
            return new ErrorDTO(e.getMessage(), e.toString());
        } catch (Exception e) {
            return new ErrorDTO(e.getMessage(), e.toString());
        }
    }
}

