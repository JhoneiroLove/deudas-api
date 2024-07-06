package com.upao.deudas.service.impl;

import com.upao.deudas.domain.dto.RegisterUser;
import com.upao.deudas.domain.entity.User;
import com.upao.deudas.infra.repository.UserRepository;
import com.upao.deudas.infra.security.JwtService;
import com.upao.deudas.infra.security.LoginRequest;
import com.upao.deudas.infra.security.TokenResponse;
import com.upao.deudas.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public TokenResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + request.getEmail()));

        if (!user.isEnabled()) {
            throw new DisabledException("Este usuario ha sido deshabilitado.");
        }

        String token = jwtService.getToken(user, user);
        return TokenResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public TokenResponse addUsuario(RegisterUser datos) {
        User user = new User();
        user.setName(datos.name());
        user.setLastname(datos.lastname());
        user.setPhone(datos.phone());
        user.setEmail(datos.email());
        user.setUsername(datos.username());
        user.setPassword(passwordEncoder.encode(datos.password()));

        userRepository.save(user);

        String token = jwtService.getToken(user, user);
        return TokenResponse.builder()
                .token(token)
                .build();
    }
}
