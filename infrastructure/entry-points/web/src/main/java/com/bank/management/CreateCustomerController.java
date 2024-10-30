package com.bank.management;

import com.bank.management.usecase.CreateCustomerUseCase;
import com.bank.management.usecase.GetAllCustomersUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CreateCustomerController {

    private final CreateCustomerUseCase createCustomerUseCase;

    public CreateCustomerController(CreateCustomerUseCase createCustomerUseCase) {
        this.createCustomerUseCase = createCustomerUseCase;
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {

        return createCustomerUseCase.apply(customer);
    }

}
