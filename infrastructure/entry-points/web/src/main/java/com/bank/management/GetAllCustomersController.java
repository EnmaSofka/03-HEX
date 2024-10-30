package com.bank.management;

import com.bank.management.usecase.GetAllCustomersUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class GetAllCustomersController {

    private final GetAllCustomersUseCase getAllCustomersUseCase;

    public GetAllCustomersController(GetAllCustomersUseCase getAllCustomersUseCase) {
        this.getAllCustomersUseCase = getAllCustomersUseCase;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return getAllCustomersUseCase.apply();
    }

}
