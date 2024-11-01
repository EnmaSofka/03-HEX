package com.bank.management.usecase;

import com.bank.management.*;
import com.bank.management.gateway.TransactionRepository;
import com.bank.management.gateway.TransactionAccountDetailRepository;

public class ProcessTransactionUseCase {

    private final TransactionRepository transactionRepository;
    private final TransactionAccountDetailRepository transactionAccountDetailRepository;

    public ProcessTransactionUseCase(TransactionRepository transactionRepository, TransactionAccountDetailRepository transactionAccountDetailRepository) {
        this.transactionRepository = transactionRepository;
        this.transactionAccountDetailRepository = transactionAccountDetailRepository;
    }

    public void process(Account account, Transaction transaction, String role) {
        Transaction savedTransaction = transactionRepository.save(transaction);
        TransactionAccountDetail txDetail = new TransactionAccountDetail.Builder()
                .transaction(savedTransaction)
                .account(account)
                .role(role)
                .build();
        transactionAccountDetailRepository.save(txDetail);
    }
}
