package com.bank.management.usecase;

import com.bank.management.BankAccount;
import com.bank.management.Deposit;
import com.bank.management.Purchase;
import com.bank.management.enums.DepositType;
import com.bank.management.enums.PurchaseType;
import com.bank.management.gateway.BankAccountRepository;
import com.bank.management.gateway.CustomerRepository;

import java.math.BigDecimal;

public class ProcessPurchaseWithCardUseCase {

    private final BankAccountRepository bankAccountRepository;

    public ProcessPurchaseWithCardUseCase(BankAccountRepository bankAccountRepository, CustomerRepository customerRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }


    public void apply(Purchase purchase) {
        BankAccount account = bankAccountRepository.findByAccountNumber(purchase.getAccountNumber());

        PurchaseType purchaseType;
        try {
            purchaseType = PurchaseType.valueOf(purchase.getType().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(purchase.getType());
        }

        BigDecimal amount = purchase.getAmount();
        BigDecimal fee = calculatePurchaseFee(purchaseType);
        BigDecimal totalCharge = amount.add(fee);

        if (account.getBalance().compareTo(totalCharge) < 0) {
            throw new RuntimeException();
        }

        account.adjustBalance(totalCharge.negate());
        bankAccountRepository.save(account);
    }

    private BigDecimal calculatePurchaseFee(PurchaseType type) {
        return switch (type) {
            case PHYSICAL -> BigDecimal.ZERO; // No fee
            case ONLINE -> new BigDecimal("5.00"); // $5 USD
            default -> throw new RuntimeException(type.toString());
        };
    }
}
