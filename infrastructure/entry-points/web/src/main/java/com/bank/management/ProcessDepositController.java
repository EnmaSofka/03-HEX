package com.bank.management;

import com.bank.management.usecase.ProcessDepositUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bank.account.management.model.dto.DepositDTO;


@RestController
@RequestMapping("/api/v1/bank-accounts/deposit")
public class ProcessDepositController {

    private final ProcessDepositUseCase processDepositUseCase;

    public ProcessDepositController(ProcessDepositUseCase processDepositUseCase) {
        this.processDepositUseCase = processDepositUseCase;
    }


    @PostMapping
    public ResponseEntity<Void> processDeposit(@RequestBody Deposit deposit) {
        processDepositUseCase.apply(deposit);
        return ResponseEntity.ok().build();
    }

}
