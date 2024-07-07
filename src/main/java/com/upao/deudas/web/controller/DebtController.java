package com.upao.deudas.web.controller;

import com.upao.deudas.domain.dto.DebtResponse;
import com.upao.deudas.service.impl.DebtServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/debts")
@CrossOrigin("*")
@RequiredArgsConstructor
public class DebtController {
    private final DebtServiceImpl debtService;

    @GetMapping("/month")
    public ResponseEntity<List<DebtResponse>> getDebtsByMonth(
            @RequestParam("year") int year,
            @RequestParam("month") int month) {
        List<DebtResponse> debts = debtService.getDebtsByMonth(year, month);
        return ResponseEntity.ok(debts);
    }
}
