package com.bank.management;

import com.bank.management.usecase.GetAllCustomersUseCase;
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
    public Customer getAllCustomers(@PathVariable Long id) {
        return getCustomerByidUseCase.apply(id);
    }

}
