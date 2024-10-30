package com.bank.management;

import com.bank.management.usecase.CreateBankAccountUseCase;
import com.bank.management.usecase.DeleteBankAccountUseCase;
import com.bank.management.usecase.GetBankAccountUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bank.account.management.model.dto.BankAccountDTO;

@RestController
@RequestMapping("/api/v1/bank-accounts")
public class GetBankAccountController {

    private final GetBankAccountUseCase getBankAccountUseCase;

    public GetBankAccountController(GetBankAccountUseCase getBankAccountUseCase) {
        this.getBankAccountUseCase = getBankAccountUseCase;
    }


    @GetMapping("/{id}")
    public BankAccount getBankAccount(@PathVariable Long id) {

        return getBankAccountUseCase.apply(id);
    }

}
