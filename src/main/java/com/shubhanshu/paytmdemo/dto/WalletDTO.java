package com.shubhanshu.paytmdemo.dto;

public class WalletDTO implements DTO{

    private Long id;
    private Long money;

    public WalletDTO(Long id, Long money) {
        this.id = id;
        this.money = money;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }
}
