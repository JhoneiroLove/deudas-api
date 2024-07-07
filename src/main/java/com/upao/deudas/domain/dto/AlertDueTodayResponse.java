package com.upao.deudas.domain.dto;

import java.util.List;

public record AlertDueTodayResponse(
        String message,
        List<DebtResponse> debtsDueToday
) {
}
