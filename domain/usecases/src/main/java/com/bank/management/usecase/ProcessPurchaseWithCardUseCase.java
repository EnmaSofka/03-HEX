package com.bank.management.usecase;

import com.bank.management.Account;
import com.bank.management.Purchase;
import com.bank.management.Transaction;
import com.bank.management.TransactionAccountDetail;
import com.bank.management.enums.PurchaseType;
import com.bank.management.exception.BankAccountNotFoundException;
import com.bank.management.exception.InsufficientFundsException;
import com.bank.management.exception.InvalidPurchaseTypeException;
import com.bank.management.gateway.AccountRepository;
import com.bank.management.gateway.CustomerRepository;
import com.bank.management.gateway.TransactionAccountDetailRepository;
import com.bank.management.gateway.TransactionRepository;

import java.math.BigDecimal;
import java.util.Optional;

public class ProcessPurchaseWithCardUseCase {

    private final AccountRepository bankAccountRepository;
    private final ProcessTransactionUseCase processTransactionUseCase;

    public ProcessPurchaseWithCardUseCase(AccountRepository bankAccountRepository, ProcessTransactionUseCase processTransactionUseCase) {
        this.bankAccountRepository = bankAccountRepository;
        this.processTransactionUseCase = processTransactionUseCase;
    }


    public Optional<Account> apply(Purchase purchase) {
        Optional<Account> accountOptional = bankAccountRepository.findByNumber(purchase.getAccountNumber());

        if (accountOptional.isEmpty()) {
            throw new BankAccountNotFoundException();
        }

        PurchaseType purchaseType;
        try {
            purchaseType = PurchaseType.valueOf(purchase.getType().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidPurchaseTypeException(purchase.getType().toUpperCase());
        }

        BigDecimal amount = purchase.getAmount();
        BigDecimal fee = calculatePurchaseFee(purchaseType);
        BigDecimal totalCharge = amount.add(fee);

        Account account = accountOptional.get();

        if (account.getAmount().compareTo(totalCharge) < 0) {
            throw new InsufficientFundsException();
        }

        account.adjustBalance(totalCharge.negate());

        Transaction trx = new Transaction.Builder()
                .amountTransaction(purchase.getAmount())
                .transactionCost(fee)
                .typeTransaction(purchaseType.toString())
                .build();

        processTransactionUseCase.process(account, trx, "RECEIVED");

        return bankAccountRepository.save(account);
    }

    private BigDecimal calculatePurchaseFee(PurchaseType type) {
        return switch (type) {
            case PHYSICAL -> BigDecimal.ZERO; // No fee
            case ONLINE -> new BigDecimal("5.00"); // $5 USD
            default -> throw new RuntimeException(type.toString());
        };
    }
}
