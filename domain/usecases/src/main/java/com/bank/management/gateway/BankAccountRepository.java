package com.bank.management.gateway;

import com.bank.management.BankAccount;

import java.util.List;

public interface BankAccountRepository {
    BankAccount findById(Long id);
    BankAccount save(BankAccount account);

    void delete(Long id);

    BankAccount findByAccountNumber(String accountNumber);
    List<BankAccount> findByCustomerId(Long id);
}