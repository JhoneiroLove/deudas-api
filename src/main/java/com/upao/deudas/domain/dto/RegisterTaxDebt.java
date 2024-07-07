package com.upao.deudas.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record RegisterTaxDebt(
        @NotBlank String numberDocument,
        @NotNull String company,
        @NotNull Double amount,
        @NotNull LocalDate dateExpiration,
        @NotNull String period,
        @NotNull Double interestRate,
        List<RegisterCollectionDetail> collectionDetails
) {
}
