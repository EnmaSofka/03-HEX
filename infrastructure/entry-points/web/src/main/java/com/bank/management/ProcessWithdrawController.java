package com.bank.management;

import com.bank.management.usecase.ProcessWithdrawUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/bank-accounts/withdraw")
public class ProcessWithdrawController {

    private final ProcessWithdrawUseCase processWithdrawUseCase;

    public ProcessWithdrawController(ProcessWithdrawUseCase processWithdrawUseCase) {
        this.processWithdrawUseCase = processWithdrawUseCase;
    }


    @PostMapping
    public ResponseEntity<Void> processWithdraw(@RequestBody Withdrawal withdrawal) {
        processWithdrawUseCase.apply(withdrawal);
        return ResponseEntity.ok().build();
    }

}
