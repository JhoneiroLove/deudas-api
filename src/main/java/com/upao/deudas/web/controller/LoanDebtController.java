package com.upao.deudas.web.controller;

import com.upao.deudas.domain.dto.RegisterLoanDebt;
import com.upao.deudas.domain.entity.LoanDebt;
import com.upao.deudas.service.impl.LoanDebtServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/loan-debts")
@RequiredArgsConstructor
public class LoanDebtController {
    private final LoanDebtServiceImpl loanDebtService;

    @PostMapping("/register")
    public ResponseEntity<LoanDebt> registerLoanDebt(@RequestBody @Valid RegisterLoanDebt registerLoanDebt, @RequestParam Long userId) {
        LoanDebt nuevaDeuda = loanDebtService.registerLoanDebt(registerLoanDebt, userId);
        return ResponseEntity.ok(nuevaDeuda);
    }
}
