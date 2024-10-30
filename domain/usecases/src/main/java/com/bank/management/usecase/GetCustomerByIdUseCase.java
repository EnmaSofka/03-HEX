package com.bank.management.usecase;

import com.bank.management.Customer;
import com.bank.management.gateway.CustomerRepository;


public class GetCustomerByIdUseCase {

    private final CustomerRepository customerRepository;

    public GetCustomerByIdUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer apply(Long id) {
        return customerRepository.findById(id);
    }
}
