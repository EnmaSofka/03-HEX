package com.bank.management.usecase;

import com.bank.management.*;
import com.bank.management.enums.DepositType;
import com.bank.management.exception.BankAccountNotFoundException;
import com.bank.management.exception.CustomerNotFoundException;
import com.bank.management.exception.InvalidAmountException;
import com.bank.management.exception.InvalidDepositTypeException;
import com.bank.management.gateway.AccountRepository;
import com.bank.management.gateway.CustomerRepository;

import java.math.BigDecimal;
import java.util.Optional;

public class ProcessDepositUseCase {

    private final AccountRepository bankAccountRepository;
    private final CustomerRepository customerRepository;
    private final ProcessTransactionUseCase processTransactionUseCase;

    public ProcessDepositUseCase(AccountRepository bankAccountRepository, CustomerRepository customerRepository, ProcessTransactionUseCase processTransactionUseCase) {
        this.bankAccountRepository = bankAccountRepository;
        this.customerRepository = customerRepository;
        this.processTransactionUseCase = processTransactionUseCase;
    }

    public Optional<Account> apply(Deposit deposit) {
        if (deposit.getAmount() == null || deposit.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException();
        }

        Optional<Account> accountOptional = bankAccountRepository.findByNumber(deposit.getAccountNumber());
        if (accountOptional.isEmpty()) {
            throw new BankAccountNotFoundException();
        }
        Optional<Customer> customerOptional = customerRepository.findByNumber(deposit.getAccountNumber());
        if (customerOptional.isEmpty()) {
            throw new CustomerNotFoundException(Long.valueOf(deposit.getAccountNumber()));
        }

        DepositType depositType;
        try {
            depositType = DepositType.valueOf(deposit.getType().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidDepositTypeException(deposit.getType().toUpperCase());
        }

        BigDecimal fee = calculateDepositFee(depositType);

        Account account = accountOptional.get();
        account.adjustBalance(deposit.getAmount().subtract(fee));

        Transaction trx = new Transaction.Builder()
                .amountTransaction(deposit.getAmount())
                .transactionCost(fee)
                .typeTransaction(depositType.toString())
                .build();

        processTransactionUseCase.process(account, trx, "RECEIVED");

        return bankAccountRepository.save(account);
    }

    private BigDecimal calculateDepositFee(DepositType type) {
        return switch (type) {
            case ATM -> new BigDecimal("2.00"); // 2 USD
            case OTHER_ACCOUNT -> new BigDecimal("1.50"); // 1.5 USD
            case BRANCH -> BigDecimal.ZERO;
            default -> throw new InvalidDepositTypeException(type.toString().toUpperCase());
        };
    }
}
