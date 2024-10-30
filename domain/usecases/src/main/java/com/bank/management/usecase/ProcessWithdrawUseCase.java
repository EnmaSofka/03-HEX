package com.bank.management.usecase;

import com.bank.management.BankAccount;
import com.bank.management.Customer;
import com.bank.management.Withdrawal;
import com.bank.management.gateway.BankAccountRepository;
import com.bank.management.gateway.CustomerRepository;

import java.math.BigDecimal;

public class ProcessWithdrawUseCase {


    private final BankAccountRepository bankAccountRepository;
    private final CustomerRepository customerRepository;

    public ProcessWithdrawUseCase(BankAccountRepository bankAccountRepository, CustomerRepository customerRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.customerRepository = customerRepository;
    }

    public void apply(Withdrawal withdrawal) {
        if (withdrawal.getAmount() == null || withdrawal.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException();
        }

        BankAccount account = bankAccountRepository.findByAccountNumber(withdrawal.getAccountNumber());

        Customer customer = customerRepository.findById(withdrawal.getCustomerId());

        BigDecimal transactionFee = new BigDecimal("1.00");
        BigDecimal totalCharge = withdrawal.getAmount().add(transactionFee);

        if (account.getBalance().compareTo(totalCharge) < 0) {
            throw new RuntimeException("Transaction fee is negative");
        }

        account.adjustBalance(totalCharge.negate());
        bankAccountRepository.save(account);
    }

}
