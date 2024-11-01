package com.bank.management.mapper;

import com.bank.management.Transaction;
import com.bank.management.data.TransactionEntity;

public class TransactionMapper {

    public static Transaction toDomain(TransactionEntity entity) {
        return new Transaction.Builder().id(entity.getId())
                .amountTransaction(entity.getAmountTransaction())
                .transactionCost(entity.getTransactionCost())
                .typeTransaction(entity.getTypeTransaction())
                .timeStamp(entity.getTimeStamp())
                .build();
    }

    public static TransactionEntity toEntity(Transaction transaction) {
        TransactionEntity entity = new TransactionEntity();
        entity.setId(transaction.getId());
        entity.setAmountTransaction(transaction.getAmountTransaction());
        entity.setTransactionCost(transaction.getTransactionCost());
        entity.setTypeTransaction(transaction.getTypeTransaction());
        entity.setTimeStamp(transaction.getTimeStamp());

        return entity;
    }
}
