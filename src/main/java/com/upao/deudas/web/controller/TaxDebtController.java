package com.upao.deudas.web.controller;

import com.upao.deudas.domain.dto.RegisterTaxDebt;
import com.upao.deudas.domain.entity.TaxDebt;
import com.upao.deudas.service.impl.TaxDebtServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tax-debts")
@RequiredArgsConstructor
public class TaxDebtController {
    private final TaxDebtServiceImpl taxDebtService;

    @PostMapping("/register")
    public ResponseEntity<TaxDebt> registerTaxDebt(@RequestBody @Valid RegisterTaxDebt registerTaxDebt, @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        TaxDebt newDebt = taxDebtService.registerTaxDebt(registerTaxDebt, authHeader.substring(7));
        return ResponseEntity.ok(newDebt);
    }
}
