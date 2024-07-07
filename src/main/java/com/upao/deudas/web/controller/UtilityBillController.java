package com.upao.deudas.web.controller;

import com.upao.deudas.domain.dto.RegisterUtilityBill;
import com.upao.deudas.domain.entity.UtilityBill;
import com.upao.deudas.service.impl.UtilityBillServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/utility-bills")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UtilityBillController {
    private final UtilityBillServiceImpl utilityBillService;

    @PostMapping("/register")
    public ResponseEntity<UtilityBill> registerInvoiceDebt(@RequestBody @Valid RegisterUtilityBill registerUtilityBill, @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        UtilityBill newDebt = utilityBillService.registerUtilityBill(registerUtilityBill, authHeader.substring(7));
        return ResponseEntity.ok(newDebt);
    }
}
