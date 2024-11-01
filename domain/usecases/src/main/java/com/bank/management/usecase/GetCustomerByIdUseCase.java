package com.bank.management.usecase;

import com.bank.management.Customer;
import com.bank.management.exception.CustomerNotFoundException;
import com.bank.management.gateway.CustomerRepository;

import javax.swing.text.html.Option;
import java.util.Optional;


public class GetCustomerByIdUseCase {

    private final CustomerRepository customerRepository;

    public GetCustomerByIdUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer apply(Long id) {

        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new CustomerNotFoundException(id);
        }
        return customer.get();
    }
}
