package com.bank.management;

import com.bank.management.config.PostgresTransactionAccountDetRepository;
import com.bank.management.data.TransactionAccountDetailEntity;
import com.bank.management.gateway.TransactionAccountDetailRepository;
import com.bank.management.mapper.TransactionAccountDetailMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionAccountDetailAdapter implements TransactionAccountDetailRepository {

    private final PostgresTransactionAccountDetRepository trxAccountDetRepository;

    public TransactionAccountDetailAdapter(PostgresTransactionAccountDetRepository trxAccountDetRepository) {
        this.trxAccountDetRepository = trxAccountDetRepository;
    }

    @Override
    public <Optional>TransactionAccountDetail save(TransactionAccountDetail trx) {
        TransactionAccountDetailEntity trxEntity = TransactionAccountDetailMapper.toEntity(trx);
        TransactionAccountDetailEntity trxSaved = trxAccountDetRepository.save(trxEntity);
        return trx;
    }
}
