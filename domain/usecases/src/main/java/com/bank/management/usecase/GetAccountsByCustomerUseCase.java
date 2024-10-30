package com.bank.management.usecase;

import com.bank.management.BankAccount;
import com.bank.management.gateway.BankAccountRepository;

import java.util.List;
import java.util.stream.Collectors;

public class GetAccountsByCustomerUseCase {

    private final BankAccountRepository bankAccountRepository;

    public GetAccountsByCustomerUseCase(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public List<BankAccount> apply(Long id) {
        return bankAccountRepository.findByCustomerId(id);
    }

}