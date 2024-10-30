package com.bank.management.mapper;

import com.bank.management.Customer;
import com.bank.management.data.CustomerEntity;
import java.util.stream.Collectors;

public class CustomerMapper {

    public static Customer toDomain(CustomerEntity entity) {
        if (entity == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setId(entity.getId());
        customer.setName(entity.getName());
        customer.setEmail(entity.getEmail());
        customer.setUsername(entity.getUsername());

        // Convertir las cuentas bancarias relacionadas
        if (entity.getAccounts() != null) {
            customer.setAccounts(entity.getAccounts().stream()
                    .map(BankAccountMapper::toDomain)
                    .collect(Collectors.toList()));
        }
        return customer;
    }

    public static CustomerEntity toEntity(Customer customer) {
        if (customer == null) {
            return null;
        }
        CustomerEntity entity = new CustomerEntity();
        entity.setId(customer.getId());
        entity.setName(customer.getName());
        entity.setEmail(customer.getEmail());
        entity.setUsername(customer.getUsername());

        // Convertir las cuentas bancarias relacionadas
        if (customer.getAccounts() != null) {
            entity.setAccounts(customer.getAccounts().stream()
                    .map(BankAccountMapper::toEntity)
                    .collect(Collectors.toList()));
        }
        return entity;
    }
}
