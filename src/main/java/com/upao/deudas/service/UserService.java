package com.upao.deudas.service;

import com.upao.deudas.domain.dto.RegisterUser;
import com.upao.deudas.infra.security.LoginRequest;
import com.upao.deudas.infra.security.TokenResponse;

public interface UserService {
    TokenResponse login(LoginRequest request);

    TokenResponse addUsuario(RegisterUser usuario);
}
