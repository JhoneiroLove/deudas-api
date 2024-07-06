package com.upao.deudas.service.impl;

import com.upao.deudas.domain.entity.Debt;
import com.upao.deudas.infra.repository.DebtRepository;
import com.upao.deudas.service.DebtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DebtServiceImpl implements DebtService {
    private final DebtRepository debtRepository;

    @Transactional
    @Override
    public Debt registrarDeuda(Debt debt) {
        Optional<Debt> existingDeuda = debtRepository.findByNumberDocument(debt.getNumberDocument());
        if (existingDeuda.isPresent()) {
            throw new IllegalArgumentException("La deuda con este número de documento ya está registrada.");
        }
        return debtRepository.save(debt);
    }
}
