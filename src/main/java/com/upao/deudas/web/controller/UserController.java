package com.upao.deudas.web.controller;

import com.upao.deudas.domain.dto.RegisterUser;
import com.upao.deudas.infra.security.LoginRequest;
import com.upao.deudas.infra.security.TokenResponse;
import com.upao.deudas.service.impl.UserServiceImpl;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserServiceImpl usuarioService;

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
            TokenResponse tokenResponse = usuarioService.login(request);
            return ResponseEntity.ok(tokenResponse);
    }

    @PostMapping("/registrar")
    @Transactional
    public ResponseEntity<TokenResponse> addUsuario(@RequestBody @Valid RegisterUser datos) {
        return ResponseEntity.ok(usuarioService.addUsuario(datos));
    }
}
