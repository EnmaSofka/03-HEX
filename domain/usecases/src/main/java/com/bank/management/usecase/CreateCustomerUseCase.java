package com.bank.management.usecase;

import com.bank.management.Customer;
import com.bank.management.gateway.CustomerRepository;

import java.util.Optional;

public class CreateCustomerUseCase {

    private final CustomerRepository customerRepository;

    public CreateCustomerUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer apply(Customer customer) {
        Optional<Customer> existingCustomer = customerRepository.findByUsername(customer.getUsername());
        if (existingCustomer.isPresent()) {
            throw new RuntimeException("Customer already exists");
        }

        return customerRepository.save(customer);
    }
}
