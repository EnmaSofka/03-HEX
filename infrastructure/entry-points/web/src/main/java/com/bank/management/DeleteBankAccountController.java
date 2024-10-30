package com.bank.management;

import com.bank.management.usecase.CreateBankAccountUseCase;
import com.bank.management.usecase.DeleteBankAccountUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bank.account.management.model.dto.BankAccountDTO;

@RestController
@RequestMapping("/api/v1/bank-accounts")
public class DeleteBankAccountController {

    private final DeleteBankAccountUseCase deleteBankAccountUseCase;

    public DeleteBankAccountController(DeleteBankAccountUseCase deleteBankAccountUseCase) {
        this.deleteBankAccountUseCase = deleteBankAccountUseCase;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {

        deleteBankAccountUseCase.apply(id);
        return ResponseEntity.noContent().build();
    }

}
