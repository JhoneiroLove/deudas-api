package com.upao.deudas.domain.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record RegisterPaymentSchedule(
        @NotNull Integer paymentNumber,
        @NotNull LocalDate dueDate,
        @NotNull Double balance,
        @NotNull Double principal,
        @NotNull Double interest,
        @NotNull Double installment
) {
}
