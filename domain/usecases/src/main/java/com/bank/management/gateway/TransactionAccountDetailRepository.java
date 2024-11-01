package com.bank.management.gateway;

import com.bank.management.TransactionAccountDetail;

public interface TransactionAccountDetailRepository {

    <Optional>TransactionAccountDetail save(TransactionAccountDetail trx);

}
