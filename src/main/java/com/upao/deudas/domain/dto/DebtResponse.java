package com.upao.deudas.domain.dto;

import java.time.LocalDate;

public record DebtResponse(
        String numberDocument,
        String company,
        Double amount,
        LocalDate dateExpiration,
        String color
) {
}
