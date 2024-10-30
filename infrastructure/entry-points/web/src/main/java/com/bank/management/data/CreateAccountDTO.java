package com.bank.account.management.model.dto;

import java.math.BigDecimal;

/**
 * Data Transfer Object for Bank Account.
 */
public class CreateAccountDTO {

    private Long customerId;
    private BigDecimal balance;

    public CreateAccountDTO() {}

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BankAccountDTO{" +
                ", customerId=" + customerId +
                ", balance=" + balance +
                '}';
    }
}
