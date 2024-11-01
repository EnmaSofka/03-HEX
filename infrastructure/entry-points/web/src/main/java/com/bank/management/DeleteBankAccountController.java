package com.bank.management;

import com.bank.management.data.ResponseDeleteBankAccountDTO;
import com.bank.management.usecase.DeleteBankAccountUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bank-accounts")
public class DeleteBankAccountController {

    private final DeleteBankAccountUseCase deleteBankAccountUseCase;

    public DeleteBankAccountController(DeleteBankAccountUseCase deleteBankAccountUseCase) {
        this.deleteBankAccountUseCase = deleteBankAccountUseCase;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDeleteBankAccountDTO> deleteBankAccount(@PathVariable Long id) {

        boolean isDeleted = deleteBankAccountUseCase.apply(id);

        if (isDeleted) {

            ResponseDeleteBankAccountDTO response = new ResponseDeleteBankAccountDTO.Builder()
                    .setMessage("Bank account deleted successfully.")
                    .setAccountNumber(String.valueOf(id)) 
                    .build();
            return ResponseEntity.ok(response);
        } else {
            ResponseDeleteBankAccountDTO response = new ResponseDeleteBankAccountDTO.Builder()
                    .setMessage("Error deleting bank account")
                    .setAccountNumber(String.valueOf(id))
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }



}
