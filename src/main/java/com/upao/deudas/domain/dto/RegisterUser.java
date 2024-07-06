package com.upao.deudas.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

public record RegisterUser(
        @NotBlank @Length(max = 100) String name,
        @NotBlank @Length(max = 100) String lastname,
        @NotNull @Size(min = 0, max = 9) String phone,
        @NotBlank @Email @Length(max = 100) String email,
        @NotBlank @Length(max = 100) String username,
        @NotBlank @Length(max = 100) String password
) {
}
