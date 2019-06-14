package com.shubhanshu.paytmdemo.model;

import com.shubhanshu.logicConstants.UserType;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class PaytmUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String LastName;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private Long phoneNumber;
    @OneToOne(cascade = CascadeType.ALL)
    private Wallet wallet;

    public PaytmUser() {

    }

    public PaytmUser(String firstName, String lastName, UserType userType, Long phoneNumber) {
        this.firstName = firstName;
        LastName = lastName;
        this.userType = userType;
        this.phoneNumber = phoneNumber;
        this.wallet = null;
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

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaytmUser)) return false;
        PaytmUser paytmUser = (PaytmUser) o;
        return Objects.equals(id, paytmUser.id) &&
                Objects.equals(firstName, paytmUser.firstName) &&
                Objects.equals(LastName, paytmUser.LastName) &&
                userType == paytmUser.userType &&
                Objects.equals(phoneNumber, paytmUser.phoneNumber) &&
                Objects.equals(wallet, paytmUser.wallet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, LastName, userType, phoneNumber, wallet);
    }

    @Override
    public String toString() {
        return "PaytmUser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", userType=" + userType +
                ", phoneNumber=" + phoneNumber +
                ", wallet=" + wallet +
                '}';
    }
}
