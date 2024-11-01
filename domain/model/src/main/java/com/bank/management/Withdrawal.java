package com.bank.management;

import java.math.BigDecimal;

public class Withdrawal {
    private final Long customerId;
    private final String accountNumber;
    private final BigDecimal amount;

    private Withdrawal(Builder builder) {
        this.customerId = builder.customerId;
        this.accountNumber = builder.accountNumber;
        this.amount = builder.amount;
    }

    public Withdrawal(Long customerId, String accountNumber, BigDecimal amount) {
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    // Getters
    public Long getCustomerId() {
        return customerId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public static class Builder {
        private Long customerId;
        private String accountNumber;
        private BigDecimal amount;

        public Builder setCustomerId(Long customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public Builder setAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Withdrawal build() {
            return new Withdrawal(this);
        }
    }

    @Override
    public String toString() {
        return "Withdrawal{" +
                "customerId=" + customerId +
                ", accountNumber='" + accountNumber + '\'' +
                ", amount=" + amount +
                '}';
    }
}
