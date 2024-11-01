package com.bank.management;

import com.bank.management.config.PostgresCustomerRepository;
import com.bank.management.data.CustomerEntity;
import com.bank.management.gateway.CustomerRepository;
import com.bank.management.mapper.CustomerMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CustomerAdapter implements CustomerRepository {

    private final PostgresCustomerRepository customerRepository;

    public CustomerAdapter(PostgresCustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll().stream()
                .map(CustomerMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Customer> findById(Long id) {

        return Optional.ofNullable(customerRepository.findById(id)
                .map(CustomerMapper::toDomain)
                .orElse(null));
    }

    @Override
    public Optional<Customer> save(Customer customer) {
        CustomerEntity entity = CustomerMapper.toEntity(customer);
        CustomerEntity savedEntity = customerRepository.save(entity);
        return Optional.ofNullable(CustomerMapper.toDomain(savedEntity));
    }

    @Override
    public Optional<Customer> findByUsername(String username) {

        return Optional.ofNullable(CustomerMapper.toDomain(customerRepository.findByUsername(username)));

    }

    @Override
    public boolean delete(Customer customer) {
        CustomerEntity entity = CustomerMapper.toEntity(customer);
        customerRepository.delete(entity);
        return true;
    }

    @Override
    public Optional<Customer> findByNumber(String accountNumber) {
        return Optional.ofNullable(CustomerMapper.toDomain(customerRepository.findByAccountNumber(accountNumber)));
    }
}
