package com.bank.management;

import com.bank.management.usecase.GetAccountsByCustomerUseCase;
import com.bank.management.usecase.GetBankAccountUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bank-accounts/customer")
public class GetAccountsByCustomerIdController {

    private final GetAccountsByCustomerUseCase getAccountsByCustomerUseCase;

    public GetAccountsByCustomerIdController(GetAccountsByCustomerUseCase getAccountsByCustomerUseCase) {
        this.getAccountsByCustomerUseCase = getAccountsByCustomerUseCase;
    }

    @GetMapping("/{id}")
    public List<BankAccount> getBankAccountByCustomer(@PathVariable Long id) {

        return getAccountsByCustomerUseCase.apply(id);
    }

}
