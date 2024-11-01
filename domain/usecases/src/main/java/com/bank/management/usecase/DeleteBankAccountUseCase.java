package com.bank.management.usecase;

import com.bank.management.Account;
import com.bank.management.exception.BankAccountNotFoundException;
import com.bank.management.gateway.AccountRepository;

import java.util.Optional;


public class DeleteBankAccountUseCase {

    private final AccountRepository bankAccountRepository;

    public DeleteBankAccountUseCase(AccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public boolean apply(Long id) {
        Optional<Account> accountOptional = bankAccountRepository.findById(id);
        if (accountOptional.isEmpty()) {
            throw new BankAccountNotFoundException();
        }
        bankAccountRepository.delete(id);
        return true;
    }

}
