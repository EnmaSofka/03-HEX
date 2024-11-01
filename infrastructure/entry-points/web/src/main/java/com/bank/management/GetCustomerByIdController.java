package com.bank.management;

import com.bank.management.data.CustomerDTO;
import com.bank.management.usecase.GetCustomerByIdUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class GetCustomerByIdController {

    private final GetCustomerByIdUseCase getCustomerByidUseCase;

    public GetCustomerByIdController(GetCustomerByIdUseCase getCustomerByidUseCase) {
        this.getCustomerByidUseCase = getCustomerByidUseCase;
    }


    @GetMapping("/{id}")
    public CustomerDTO getAllCustomers(@PathVariable Long id) {
        Customer customer = getCustomerByidUseCase.apply(id);

        return new CustomerDTO.Builder()
                .setUsername(customer.getUsername())
                .build();
    }

}
