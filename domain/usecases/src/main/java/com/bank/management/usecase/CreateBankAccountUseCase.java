package com.bank.management.usecase;

import com.bank.management.Account;
import com.bank.management.Customer;
import com.bank.management.exception.BankAccountAlreadyExistsException;
import com.bank.management.exception.CustomerNotFoundException;
import com.bank.management.gateway.AccountRepository;
import com.bank.management.gateway.CustomerRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;


public class CreateBankAccountUseCase {

    private final AccountRepository bankAccountRepository;
    private final CustomerRepository customerRepository;

    public CreateBankAccountUseCase(AccountRepository bankAccountRepository, CustomerRepository customerRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.customerRepository = customerRepository;
    }


    public Account apply(Account account, Customer customer) {

        Optional<Customer> customerOptional = customerRepository.findById(customer.getId());
        if (customerOptional.isEmpty()) {
            throw new CustomerNotFoundException(customer.getId());
        }
        Customer customerFound = customerOptional.get();

        Account accountToCreate = new Account.Builder()
                .number(generateAccountNumber())
                .amount(account.getAmount())
                .build();

        accountToCreate.setCustomer(customerFound);
        Optional<Account> accountCreated = bankAccountRepository.save(accountToCreate);
        return accountCreated.orElse(null);
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
