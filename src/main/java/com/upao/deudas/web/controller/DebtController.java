package com.upao.deudas.web.controller;

import com.upao.deudas.domain.entity.Debt;
import com.upao.deudas.service.impl.DebtServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/debts")
@RequiredArgsConstructor
public class DebtController {
    private final DebtServiceImpl debtService;

    @PostMapping("/register")
    public ResponseEntity<Debt> registrarDeuda(@RequestBody @Valid Debt debt) {
        Debt newDebt = debtService.registrarDeuda(debt);
        return ResponseEntity.ok(newDebt);
    }
}
