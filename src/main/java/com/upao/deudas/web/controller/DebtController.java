package com.upao.deudas.web.controller;

import com.upao.deudas.domain.dto.AlertDueTodayResponse;
import com.upao.deudas.domain.dto.DebtResponse;
import com.upao.deudas.service.impl.DebtServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/debts")
@RequiredArgsConstructor
public class DebtController {
    private final DebtServiceImpl debtService;

    @GetMapping("/month")
    public ResponseEntity<List<DebtResponse>> getDebtsByMonth(@RequestParam int year, @RequestParam int month) {
        List<DebtResponse> debts = debtService.getDebtsByMonth(year, month);
        return ResponseEntity.ok(debts);
    }

    @PostMapping("/mark-as-paid/{debtId}")
    public ResponseEntity<DebtResponse> markDebtAsPaid(@PathVariable Long debtId) {
        DebtResponse updatedDebt = debtService.markDebtAsPaid(debtId);
        return ResponseEntity.ok(updatedDebt);
    }

    @GetMapping("/alert-due-today")
    public ResponseEntity<AlertDueTodayResponse> alertDueToday() {
        AlertDueTodayResponse alertResponse = debtService.getDebtsDueToday();
        return ResponseEntity.ok(alertResponse);
    }
}
