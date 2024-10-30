package com.bank.management.usecase;

import com.bank.management.BankAccount;
import com.bank.management.Customer;
import com.bank.management.Deposit;
import com.bank.management.enums.DepositType;
import com.bank.management.gateway.BankAccountRepository;
import com.bank.management.gateway.CustomerRepository;

import java.math.BigDecimal;

public class ProcessDepositUseCase {

    private final BankAccountRepository bankAccountRepository;
    private final CustomerRepository customerRepository;

    public ProcessDepositUseCase(BankAccountRepository bankAccountRepository, CustomerRepository customerRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.customerRepository = customerRepository;
    }

    public void apply(Deposit deposit) {
        if (deposit.getAmount() == null || deposit.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Invalid deposit amount");
        }

        BankAccount account = bankAccountRepository.findByAccountNumber(deposit.getAccountNumber());

        DepositType depositType;
        try {
            depositType = DepositType.valueOf(deposit.getType().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Deposit type incorrect");
        }

        BigDecimal fee = calculateDepositFee(deposit.getAmount(), depositType);

        account.adjustBalance(deposit.getAmount().subtract(fee));
        bankAccountRepository.save(account);
    }

    private BigDecimal calculateDepositFee(BigDecimal amount, DepositType type) {
        return switch (type) {
            case ATM -> new BigDecimal("2.00"); // 2 USD
            case OTHER_ACCOUNT -> new BigDecimal("1.50"); // 1.5 USD
            case BRANCH -> BigDecimal.ZERO;
            default -> throw new RuntimeException("Invalid Deposit");
        };
    }
}
