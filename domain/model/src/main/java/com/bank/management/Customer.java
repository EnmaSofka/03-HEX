package com.bank.management;

import java.util.Date;
import java.util.List;

public class Customer {

    private Long id;
    private String username;
    private List<Account> accounts;
    private Date created_at;

    private Customer(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.accounts = builder.accounts;
        this.created_at = builder.created_at != null ? builder.created_at : new Date();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public static class Builder {
        private Long id;
        private String username;
        private List<Account> accounts;
        private Date created_at;

        public Builder() {}

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder accounts(List<Account> accounts) {
            this.accounts = accounts;
            return this;
        }

        public Builder createdAt(Date created_at) {
            this.created_at = created_at;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
