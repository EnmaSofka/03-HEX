package com.bank.management.usecase;

import com.bank.management.Customer;
import com.bank.management.gateway.CustomerRepository;

import java.util.List;

public class DeleteCustomerUseCase{

    private final CustomerRepository customerRepository;

    public DeleteCustomerUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void apply(Long id) {
        Customer customer = customerRepository.findById(id);
        customerRepository.delete(customer);
    }
}
