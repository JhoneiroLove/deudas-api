package com.upao.deudas.domain.dto;

import jakarta.validation.constraints.NotNull;

public record RegisterInvoiceDetail(
        @NotNull String product,
        @NotNull Double price,
        @NotNull Integer quantity
) {
}