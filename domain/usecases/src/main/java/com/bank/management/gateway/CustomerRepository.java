package com.bank.management.gateway;

import com.bank.management.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    List<Customer> findAll();
    Customer findById(Long id);
    Customer save(Customer customer);
    Optional<Customer> findByUsername(String username);
    void delete(Customer customer);
}
