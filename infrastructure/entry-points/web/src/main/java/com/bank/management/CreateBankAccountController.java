package com.bank.management;

import com.bank.management.usecase.CreateBankAccountUseCase;
import org.springframework.web.bind.annotation.*;
import com.bank.account.management.model.dto.CreateAccountDTO;

@RestController
@RequestMapping("/api/v1/bank-accounts")
public class CreateBankAccountController {

    private final CreateBankAccountUseCase createBankAccountUseCase;

    public CreateBankAccountController(CreateBankAccountUseCase createBankAccountUseCase) {
        this.createBankAccountUseCase = createBankAccountUseCase;
    }

    @PostMapping
    public BankAccount createCustomer(@RequestBody CreateAccountDTO createAccount) {

        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(createAccount.getBalance());

        Customer customer = new Customer();
        customer.setId(createAccount.getCustomerId());

        return createBankAccountUseCase.apply(bankAccount, customer);
    }

}
