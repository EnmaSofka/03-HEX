package com.bank.management.data;

import java.math.BigDecimal;

/**
 * Data Transfer Object for Bank Account.
 */
public class RequestCreateAccountDTO {

    private Long customerId;
    private BigDecimal amount;

    public RequestCreateAccountDTO() {}

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "BankAccountDTO{" +
                ", customerId=" + customerId +
                ", amount=" + amount +
                '}';
    }
}
