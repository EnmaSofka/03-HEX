package com.bank.management.gateway;

import com.bank.management.Transaction;

public interface TransactionRepository {
    <Optional>Transaction save(Transaction trx);
}
