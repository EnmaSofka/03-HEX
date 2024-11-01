package com.bank.management.exception;


public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long id) {
        super("Customer with ID " + id + " doesn't exist.");
    }
}