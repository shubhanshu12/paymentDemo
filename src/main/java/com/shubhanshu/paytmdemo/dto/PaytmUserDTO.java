package com.shubhanshu.paytmdemo.dto;

import com.shubhanshu.LogicConstants.UserType;

public class PaytmUserDTO implements DTO {
    private Long id;
    private String firstName;
    private String LastName;
    private UserType userType;
    private Long phoneNumber;

    public PaytmUserDTO(Long id, String firstName, String lastName, UserType userType, Long phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        LastName = lastName;
        this.userType = userType;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
