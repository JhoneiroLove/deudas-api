package com.upao.deudas.web.controller;

import com.upao.deudas.domain.dto.RegisterInvoiceDebt;
import com.upao.deudas.domain.entity.InvoiceDebt;
import com.upao.deudas.service.impl.InvoiceDebtServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/invoice-debts")
@CrossOrigin("*")
@RequiredArgsConstructor
public class InvoiceDebtController {
    private final InvoiceDebtServiceImpl invoiceDebtService;

    @PostMapping("/register")
    public ResponseEntity<InvoiceDebt> registerInvoiceDebt(@RequestBody @Valid RegisterInvoiceDebt registerInvoiceDebt, @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        InvoiceDebt newDebt = invoiceDebtService.registerInvoiceDebt(registerInvoiceDebt, authHeader.substring(7));
        return ResponseEntity.ok(newDebt);
    }
}
