package com.bank.management.usecase;

import com.bank.management.BankAccount;
import com.bank.management.Customer;
import com.bank.management.gateway.BankAccountRepository;
import com.bank.management.gateway.CustomerRepository;

import java.util.List;
import java.util.Random;


public class CreateBankAccountUseCase {

    private final BankAccountRepository bankAccountRepository;
    private final CustomerRepository customerRepository;

    public CreateBankAccountUseCase(BankAccountRepository bankAccountRepository, CustomerRepository customerRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.customerRepository = customerRepository;
    }


    public BankAccount apply(BankAccount account, Customer customer) {

        Customer customerFound = customerRepository.findById(customer.getId());
        if (customerFound == null) {
            throw new RuntimeException("Not found customer: " + customer.getId());
        }

        account.setAccountNumber(generateAccountNumber());

        List<BankAccount> existingAccounts = customerFound.getAccounts();
        existingAccounts.add(account);
        customerFound.setAccounts(existingAccounts);

        customerRepository.save(customerFound);

        return account;
    }

    private String generateAccountNumber() {

        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            accountNumber.append(random.nextInt(10));
        }
        return accountNumber.toString();
    }
}
