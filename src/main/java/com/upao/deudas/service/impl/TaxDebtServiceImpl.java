package com.upao.deudas.service.impl;

import com.upao.deudas.domain.dto.RegisterTaxDebt;
import com.upao.deudas.domain.entity.TaxDebt;
import com.upao.deudas.domain.entity.User;
import com.upao.deudas.infra.repository.TaxDebtRepository;
import com.upao.deudas.infra.repository.UserRepository;
import com.upao.deudas.infra.security.JwtService;
import com.upao.deudas.service.TaxtDebtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaxDebtServiceImpl implements TaxtDebtService {
    private final TaxDebtRepository taxDebtRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Transactional
    public TaxDebt registerTaxDebt(RegisterTaxDebt registerTaxDebt, String token) {
        String email = jwtService.getCorreoFromToken(token);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));

        TaxDebt taxDebt = new TaxDebt();
        taxDebt.setNumberDocument(registerTaxDebt.numberDocument());
        taxDebt.setCompany(registerTaxDebt.company());
        taxDebt.setAmount(registerTaxDebt.amount());
        taxDebt.setDateExpiration(registerTaxDebt.dateExpiration());
        taxDebt.setPeriod(registerTaxDebt.period());
        taxDebt.setName(registerTaxDebt.name());
        taxDebt.setAddress(registerTaxDebt.address());
        taxDebt.setUser(user);

        return taxDebtRepository.save(taxDebt);
    }
}
