package com.bank.management.usecase;

import com.bank.management.BankAccount;
import com.bank.management.Customer;
import com.bank.management.gateway.BankAccountRepository;
import com.bank.management.gateway.CustomerRepository;

import java.util.Random;


public class DeleteBankAccountUseCase {

    private final BankAccountRepository bankAccountRepository;

    public DeleteBankAccountUseCase(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public void apply(Long id) {
        bankAccountRepository.delete(id);
    }

}
