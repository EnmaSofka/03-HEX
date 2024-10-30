package com.bank.management.mapper;

import com.bank.management.BankAccount;
import com.bank.management.Customer;
import com.bank.management.data.BankAccountEntity;

public class BankAccountMapper {

    public static BankAccount toDomain(BankAccountEntity entity) {
        if (entity == null) {
            return null;
        }

        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(entity.getId());
        bankAccount.setAccountNumber(entity.getAccountNumber());
        bankAccount.setBalance(entity.getBalance());

        return bankAccount;
    }

    public static BankAccountEntity toEntity(BankAccount bankAccount) {
        if (bankAccount == null) {
            return null;
        }
        BankAccountEntity entity = new BankAccountEntity();
        entity.setId(bankAccount.getId());
        entity.setAccountNumber(bankAccount.getAccountNumber());
        entity.setBalance(bankAccount.getBalance());

        return entity;
    }
}
