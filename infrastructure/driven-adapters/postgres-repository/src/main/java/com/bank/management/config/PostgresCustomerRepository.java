package com.bank.management.config;

import com.bank.management.data.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresCustomerRepository extends JpaRepository<CustomerEntity, Long> {
    CustomerEntity findByUsername(String username);
}
