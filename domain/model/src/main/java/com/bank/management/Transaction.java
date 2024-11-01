package com.bank.management;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Transaction {

    private Long id;
    private BigDecimal amountTransaction;
    private BigDecimal transactionCost;
    private String typeTransaction;
    private Date timeStamp;
    private List<TransactionAccountDetail> transactionAccountDetails;

    private Transaction(Builder builder) {
        this.id = builder.id;
        this.amountTransaction = builder.amountTransaction;
        this.transactionCost = builder.transactionCost;
        this.typeTransaction = builder.typeTransaction;
        this.timeStamp = builder.timeStamp;
        this.transactionAccountDetails = builder.transactionAccountDetails;
    }

    // Getters

    public Long getId() {
        return id;
    }

    public BigDecimal getAmountTransaction() {
        return amountTransaction;
    }

    public BigDecimal getTransactionCost() {
        return transactionCost;
    }

    public String getTypeTransaction() {
        return typeTransaction;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public List<TransactionAccountDetail> getTransactionAccountDetails() {
        return transactionAccountDetails;
    }

    // Builder estático
    public static class Builder {
        private Long id;
        private BigDecimal amountTransaction;
        private BigDecimal transactionCost;
        private String typeTransaction;
        private Date timeStamp;
        private List<TransactionAccountDetail> transactionAccountDetails;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder amountTransaction(BigDecimal amountTransaction) {
            this.amountTransaction = amountTransaction;
            return this;
        }

        public Builder transactionCost(BigDecimal transactionCost) {
            this.transactionCost = transactionCost;
            return this;
        }

        public Builder typeTransaction(String typeTransaction) {
            this.typeTransaction = typeTransaction;
            return this;
        }

        public Builder timeStamp(Date timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public Builder transactionAccountDetails(List<TransactionAccountDetail> transactionAccountDetails) {
            this.transactionAccountDetails = transactionAccountDetails;
            return this;
        }

        public Transaction build() {
            return new Transaction(this);
        }
    }
}
