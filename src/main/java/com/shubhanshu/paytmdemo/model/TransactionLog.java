package com.shubhanshu.paytmdemo.model;

import com.shubhanshu.logicConstants.TransactionStatus;
import com.shubhanshu.logicConstants.TransactionType;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class TransactionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private PaytmUser fromUser;
    @OneToOne(cascade = CascadeType.ALL)
    private PaytmUser toUser;
    private Long amountSent;
    @Enumerated(EnumType.ORDINAL)
    private TransactionType transactionType;
    @CreationTimestamp
    private LocalDateTime createDateTime;
    @Enumerated(EnumType.ORDINAL)
    private TransactionStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaytmUser getFromUser() {
        return fromUser;
    }

    public void setFromUser(PaytmUser fromUser) {
        this.fromUser = fromUser;
    }

    public PaytmUser getToUser() {
        return toUser;
    }

    public void setToUser(PaytmUser toUser) {
        this.toUser = toUser;
    }

    public Long getAmountSent() {
        return amountSent;
    }

    public void setAmountSent(Long amountSent) {
        this.amountSent = amountSent;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionLog)) return false;
        TransactionLog that = (TransactionLog) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(fromUser, that.fromUser) &&
                Objects.equals(toUser, that.toUser) &&
                Objects.equals(amountSent, that.amountSent) &&
                transactionType == that.transactionType &&
                Objects.equals(createDateTime, that.createDateTime) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fromUser, toUser, amountSent, transactionType, createDateTime, status);
    }

    @Override
    public String toString() {
        return "TransactionLog{" +
                "id=" + id +
                ", fromUser=" + fromUser +
                ", toUser=" + toUser +
                ", amountSent=" + amountSent +
                ", transactionType=" + transactionType +
                ", createDateTime=" + createDateTime +
                ", status=" + status +
                '}';
    }
}
