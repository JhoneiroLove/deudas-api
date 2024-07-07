package com.upao.deudas.web.controller;

import com.upao.deudas.domain.dto.RegisterLoanDebt;
import com.upao.deudas.domain.entity.LoanDebt;
import com.upao.deudas.service.impl.LoanDebtServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/loan-debts")
@CrossOrigin("*")
@RequiredArgsConstructor
public class LoanDebtController {
    private final LoanDebtServiceImpl loanDebtService;

    @PostMapping("/register")
    public ResponseEntity<LoanDebt> registerLoanDebt(@RequestBody @Valid RegisterLoanDebt registerLoanDebt, @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        LoanDebt nuevaDeuda = loanDebtService.registerLoanDebt(registerLoanDebt, authHeader.substring(7));
        return ResponseEntity.ok(nuevaDeuda);
    }
}
