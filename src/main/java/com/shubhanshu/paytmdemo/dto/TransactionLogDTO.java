package com.shubhanshu.paytmdemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.shubhanshu.logicConstants.TransactionStatus;
import com.shubhanshu.logicConstants.TransactionType;

import java.time.LocalDateTime;

public class TransactionLogDTO implements DTO {

    private Long id;
    private Long fromPhoneNumber;
    private Long toPhoneNumber;
    private Long amountSent;
    private LocalDateTime createDateTime;
    private TransactionStatus status;
    private TransactionType transactionType;

    public TransactionLogDTO(@JsonProperty("id") Long id,
                             @JsonProperty("fromPhoneNumber") Long fromPhoneNumber,
                             @JsonProperty("toPhoneNumber") Long toPhoneNumber,
                             @JsonProperty("amountSent") Long amountSent,
                             @JsonSerialize(using = LocalDateTimeSerializer.class)
                             @JsonDeserialize(using = LocalDateTimeDeserializer.class)
                             @JsonProperty("createDateTime") LocalDateTime createDateTime,
                             @JsonProperty("status") TransactionStatus status,
                             @JsonProperty("transactionType") TransactionType transactionType) {
        this.id = id;
        this.fromPhoneNumber = fromPhoneNumber;
        this.toPhoneNumber = toPhoneNumber;
        this.amountSent = amountSent;
        this.createDateTime = createDateTime;
        this.status = status;
        this.transactionType = transactionType;
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

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }



    @Override
    public String toString() {
        return "TransactionLogDTO{" +
                "id=" + id +
                ", fromPhoneNumber=" + fromPhoneNumber +
                ", toPhoneNumber=" + toPhoneNumber +
                ", amountSent=" + amountSent +
                ", createDateTime=" + createDateTime +
                ", status=" + status +
                ", transactionType=" + transactionType +
                '}';
    }

}
