package com.bank.management.usecase;

import com.bank.management.*;
import com.bank.management.gateway.BankAccountRepository;
import com.bank.management.gateway.CustomerRepository;
import com.bank.management.enums.DepositType;
import com.bank.management.enums.PurchaseType;

import java.math.BigDecimal;
import java.util.Random;

public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final CustomerRepository customerRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository,
                                  CustomerRepository customerRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.customerRepository = customerRepository;
    }



    public void processDeposit(Deposit deposit) {
        if (deposit.getAmount() == null || deposit.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException();
        }

        BankAccount account = bankAccountRepository.findByAccountNumber(deposit.getAccountNumber());

        DepositType depositType;
        try {
            depositType = DepositType.valueOf(deposit.getType().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException();
        }

        BigDecimal fee = calculateDepositFee(deposit.getAmount(), depositType);

        account.adjustBalance(deposit.getAmount().subtract(fee));
        bankAccountRepository.save(account);
    }

    public void processCardPurchase(Purchase purchase) {
        BankAccount account = bankAccountRepository.findByAccountNumber(purchase.getAccountNumber());

        PurchaseType purchaseType;
        try {
            purchaseType = PurchaseType.valueOf(purchase.getType().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid purchase type");
        }

        BigDecimal amount = purchase.getAmount();
        BigDecimal fee = calculatePurchaseFee(purchaseType);
        BigDecimal totalCharge = amount.add(fee);

        if (account.getBalance().compareTo(totalCharge) < 0) {
            throw new RuntimeException("Invalid");
        }

        account.adjustBalance(totalCharge.negate());
        bankAccountRepository.save(account);
    }

    public void processWithdrawal(Withdrawal withdrawal) {
        if (withdrawal.getAmount() == null || withdrawal.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Invalid");
        }

        BankAccount account = bankAccountRepository.findByAccountNumber(withdrawal.getAccountNumber());

        BigDecimal transactionFee = new BigDecimal("1.00");
        BigDecimal totalCharge = withdrawal.getAmount().add(transactionFee);

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
            default -> throw new RuntimeException("Unknown purchase type");
        };
    }


    private BigDecimal calculateDepositFee(BigDecimal amount, DepositType type) {
        return switch (type) {
            case ATM -> new BigDecimal("2.00"); // 2 USD
            case OTHER_ACCOUNT -> new BigDecimal("1.50"); // 1.5 USD
            case BRANCH -> BigDecimal.ZERO;
            default -> throw new RuntimeException("Unknown deposit type");
        };
    }


    public BankAccount getAccount(Long id) {
        return bankAccountRepository.findById(id);
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
