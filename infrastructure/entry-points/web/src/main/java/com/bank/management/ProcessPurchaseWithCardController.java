package com.bank.management;

import com.bank.management.usecase.ProcessDepositUseCase;
import com.bank.management.usecase.ProcessPurchaseWithCardUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bank.account.management.model.dto.DepositDTO;


@RestController
@RequestMapping("/api/v1/bank-accounts/purchase-card")
public class ProcessPurchaseWithCardController {

    private final ProcessPurchaseWithCardUseCase processPurchaseWithCardUseCase;

    public ProcessPurchaseWithCardController(ProcessPurchaseWithCardUseCase processPurchaseWithCardUseCase) {
        this.processPurchaseWithCardUseCase = processPurchaseWithCardUseCase;
    }


    @PostMapping
    public ResponseEntity<Void> processPurchase(@RequestBody Purchase purchase) {
        processPurchaseWithCardUseCase.apply(purchase);
        return ResponseEntity.ok().build();
    }

}
