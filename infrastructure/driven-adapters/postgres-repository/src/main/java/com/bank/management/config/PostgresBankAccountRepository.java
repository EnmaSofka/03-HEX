package com.bank.management.config;

import com.bank.management.data.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostgresBankAccountRepository extends JpaRepository<BankAccountEntity, Long> {
    List<BankAccountEntity> findByCustomerId(Long customerId);
    Optional<BankAccountEntity> findByAccountNumber(String accountNumber);
}
