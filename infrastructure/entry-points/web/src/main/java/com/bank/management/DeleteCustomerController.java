package com.bank.management;

import com.bank.management.usecase.CreateCustomerUseCase;
import com.bank.management.usecase.DeleteCustomerUseCase;
import com.bank.management.usecase.GetAllCustomersUseCase;
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
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {

        deleteCustomerUseCase.apply(id);

        return ResponseEntity.noContent().build();
    }

}
