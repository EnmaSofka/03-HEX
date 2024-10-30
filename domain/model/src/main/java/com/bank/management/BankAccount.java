package com.bank.management;

import java.math.BigDecimal;

public class BankAccount {

    private Long id;
    private BigDecimal balance;
    private String accountNumber;

    public BankAccount(Long id, String accountNumber, BigDecimal balance) {
    }

    public BankAccount() {
    }

    public void adjustBalance(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
