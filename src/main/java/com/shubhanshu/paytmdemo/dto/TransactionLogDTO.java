package com.shubhanshu.paytmdemo.dto;

import java.time.LocalDateTime;

public class TransactionLogDTO implements DTO {

    private Long id;
    private Long fromPhoneNumber;
    private Long toPhoneNumber;
    private Long amountSent;
    private LocalDateTime createDateTime;

    public TransactionLogDTO(Long id, Long fromPhoneNumber, Long toPhoneNumber, Long amountSent, LocalDateTime createDateTime) {
        this.id = id;
        this.fromPhoneNumber = fromPhoneNumber;
        this.toPhoneNumber = toPhoneNumber;
        this.amountSent = amountSent;
        this.createDateTime = createDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFromPhoneNumber() {
        return fromPhoneNumber;
    }

    public void setFromPhoneNumber(Long fromPhoneNumber) {
        this.fromPhoneNumber = fromPhoneNumber;
    }

    public Long getToPhoneNumber() {
        return toPhoneNumber;
    }

    public void setToPhoneNumber(Long toPhoneNumber) {
        this.toPhoneNumber = toPhoneNumber;
    }

    public Long getAmountSent() {
        return amountSent;
    }

    public void setAmountSent(Long amountSent) {
        this.amountSent = amountSent;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }
}
