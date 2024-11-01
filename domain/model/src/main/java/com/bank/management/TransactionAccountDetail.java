package com.bank.management;

public class TransactionAccountDetail {

    private Transaction transaction;
    private Account account;
    private String role; // Ej: "SENDER" o "RECEIVER"

    public TransactionAccountDetail(Builder builder) {
        this.transaction = builder.transaction;
        this.account = builder.account;
        this.role = builder.role;
    }

    // Getters

    public Transaction getTransaction() {
        return transaction;
    }

    public Account getAccount() {
        return account;
    }

    public String getRole() {
        return role;
    }

    // Builder estático
    public static class Builder {
        private Transaction transaction;
        private Account account;
        private String role;

        public Builder transaction(Transaction transaction) {
            this.transaction = transaction;
            return this;
        }

        public Builder account(Account account) {
            this.account = account;
            return this;
        }

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public TransactionAccountDetail build() {
            return new TransactionAccountDetail(this);
        }
    }
}
