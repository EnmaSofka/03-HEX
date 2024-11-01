package com.bank.management;

import com.bank.management.config.PostgresTransacionRepository;
import com.bank.management.data.TransactionEntity;
import com.bank.management.gateway.TransactionRepository;
import com.bank.management.mapper.TransactionMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionAdapter implements TransactionRepository {

    private final PostgresTransacionRepository transactionRepository;

    public TransactionAdapter(PostgresTransacionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;

    }

    @Override
    public <Optional>Transaction save(Transaction trx) {
        TransactionEntity txEntity = TransactionMapper.toEntity(trx);
        TransactionEntity txSaved = transactionRepository.save(txEntity);
        return TransactionMapper.toDomain(txSaved);
    }
}
