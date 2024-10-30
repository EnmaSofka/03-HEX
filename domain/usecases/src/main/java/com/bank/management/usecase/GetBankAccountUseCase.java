package com.bank.management.usecase;

import com.bank.management.BankAccount;
import com.bank.management.Customer;
import com.bank.management.gateway.BankAccountRepository;
import com.bank.management.gateway.CustomerRepository;

import java.util.Random;


public class GetBankAccountUseCase {

    private final BankAccountRepository bankAccountRepository;

    public GetBankAccountUseCase(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public BankAccount apply(Long id) {
        return bankAccountRepository.findById(id);
    }

}
