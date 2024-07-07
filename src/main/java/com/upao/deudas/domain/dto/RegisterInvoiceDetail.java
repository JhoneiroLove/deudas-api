package com.upao.deudas.domain.dto;

import jakarta.validation.constraints.NotNull;

public record RegisterInvoiceDetail(
        @NotNull Integer quantity,
        @NotNull String product,
        @NotNull Double price
) {
}