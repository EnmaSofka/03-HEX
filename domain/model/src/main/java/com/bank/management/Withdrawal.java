package com.bank.management;

import java.math.BigDecimal;

public class Withdrawal {
    private Long customerId;
    private String accountNumber;
    private BigDecimal amount;

    public Withdrawal() {}

    public Withdrawal(Long customerId, String accountNumber, BigDecimal amount) {
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    // Getters y setters
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
