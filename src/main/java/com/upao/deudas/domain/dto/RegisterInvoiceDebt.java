package com.upao.deudas.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record RegisterInvoiceDebt(
        @NotBlank String numberDocument,
        @NotNull String company,
        @NotNull Double amount,
        @NotNull LocalDate dateExpiration,
        @NotBlank String clientName,
        @NotBlank String clientAddress,
        @NotBlank String paymentMethod,
        List<RegisterInvoiceDetail> invoiceDetails
) {
}
