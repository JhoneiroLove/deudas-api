package com.upao.deudas.domain.dto;

import jakarta.validation.constraints.NotNull;

public record RegisterCollectionDetail(
        @NotNull String description,
        @NotNull double amount
) {
}
