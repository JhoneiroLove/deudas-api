package com.upao.deudas.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RegisterTaxDebt(
        @NotBlank String numberDocument,
        @NotNull String company,
        @NotNull Double amount,
        @NotNull LocalDate dateExpiration,
        String period,
        String name,
        String address
) {
}
