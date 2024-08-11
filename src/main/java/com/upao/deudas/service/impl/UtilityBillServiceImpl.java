package com.upao.deudas.service.impl;

import com.upao.deudas.domain.dto.RegisterUtilityBill;
import com.upao.deudas.domain.entity.UtilityBill;
import com.upao.deudas.domain.entity.User;
import com.upao.deudas.infra.repository.UtilityBillRepository;
import com.upao.deudas.infra.repository.UserRepository;
import com.upao.deudas.infra.security.JwtService;
import com.upao.deudas.service.UtilityBillService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UtilityBillServiceImpl implements UtilityBillService {
    private final UtilityBillRepository utilityBillRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Transactional
    @Override
    public UtilityBill registerUtilityBill(RegisterUtilityBill registerUtilityBill, String token) {
        String email = jwtService.getCorreoFromToken(token);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));

        boolean exists = utilityBillRepository.existsByNumberDocumentAndUser(registerUtilityBill.numberDocument(), user);
        if (exists) {
            throw new IllegalArgumentException("Ya existe una deuda con este número de documento para este usuario.");
        }

        UtilityBill utilityBill = new UtilityBill();
        utilityBill.setNumberDocument(registerUtilityBill.numberDocument());
        utilityBill.setCompany(registerUtilityBill.company());
        utilityBill.setAmount(registerUtilityBill.amount());
        utilityBill.setDateExpiration(registerUtilityBill.dateExpiration());
        utilityBill.setClientName(registerUtilityBill.clientName());
        utilityBill.setClientAddress(registerUtilityBill.clientAddress());
        utilityBill.setUser(user);

        return utilityBillRepository.save(utilityBill);
    }
}
