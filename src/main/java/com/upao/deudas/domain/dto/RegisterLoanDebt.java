package com.upao.deudas.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record RegisterLoanDebt(
        @NotBlank String numberDocument,
        @NotNull String company,
        @NotNull Double amount,
        @NotNull LocalDate dateExpiration,
        @NotNull Double interestRate,
        @NotNull Integer termInMonths,
        List<RegisterPaymentSchedule> paymentSchedule
) {
}
