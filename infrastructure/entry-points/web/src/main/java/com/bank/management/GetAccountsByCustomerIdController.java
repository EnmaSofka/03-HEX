package com.bank.management;

import com.bank.management.data.BankAccountDTO;
import com.bank.management.usecase.GetAccountsByCustomerUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/bank-accounts/customer")
public class GetAccountsByCustomerIdController {

    private final GetAccountsByCustomerUseCase getAccountsByCustomerUseCase;

    public GetAccountsByCustomerIdController(GetAccountsByCustomerUseCase getAccountsByCustomerUseCase) {
        this.getAccountsByCustomerUseCase = getAccountsByCustomerUseCase;
    }

    @GetMapping("/{id}")
    public List<BankAccountDTO> getBankAccountByCustomer(@PathVariable Long id) {
        List<Account> accounts = getAccountsByCustomerUseCase.apply(id);

        return accounts.stream()
                .map(account -> new BankAccountDTO.Builder()
                        .number(account.getNumber())
                        .amount(account.getAmount())
                        .build())
                .collect(Collectors.toList());
    }

}
