package com.upao.deudas.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RegisterDebt(
    @NotBlank String numberDocument,
    @NotNull String company,
    @NotBlank Double amount,
    @NotBlank LocalDate dateExpiration,
    @NotBlank String color
) {
}
