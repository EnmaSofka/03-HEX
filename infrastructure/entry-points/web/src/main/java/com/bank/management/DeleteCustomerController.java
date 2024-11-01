package com.bank.management;

import com.bank.management.data.ResponseDeleteBankAccountDTO;
import com.bank.management.usecase.CreateCustomerUseCase;
import com.bank.management.usecase.DeleteCustomerUseCase;
import com.bank.management.usecase.GetAllCustomersUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class DeleteCustomerController {

    private final DeleteCustomerUseCase deleteCustomerUseCase;

    public DeleteCustomerController(DeleteCustomerUseCase deleteCustomerUseCase) {
        this.deleteCustomerUseCase = deleteCustomerUseCase;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDeleteBankAccountDTO> deleteCustomer(@PathVariable Long id) {
        boolean isDeleted = deleteCustomerUseCase.apply(id);

        if (isDeleted) {
            ResponseDeleteBankAccountDTO response = new ResponseDeleteBankAccountDTO.Builder()
                    .setMessage("Customer deleted successfully.")
                    .setAccountNumber(String.valueOf(id))
                    .build();
            return ResponseEntity.ok(response);
        } else {
            ResponseDeleteBankAccountDTO response = new ResponseDeleteBankAccountDTO.Builder()
                    .setMessage("Error deleting customer")
                    .setAccountNumber(String.valueOf(id))
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


}
