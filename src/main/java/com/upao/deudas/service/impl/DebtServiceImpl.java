package com.upao.deudas.service.impl;

import com.upao.deudas.domain.dto.DebtResponse;
import com.upao.deudas.domain.entity.Debt;
import com.upao.deudas.infra.repository.DebtRepository;
import com.upao.deudas.service.DebtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DebtServiceImpl implements DebtService {
    private final DebtRepository debtRepository;

    @Override
    public List<DebtResponse> getDebtsByMonth(int year, int month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);

        List<Debt> debts = debtRepository.findAllByDateExpirationBetween(startDate, endDate);
        List<DebtResponse> debtResponses = new ArrayList<>();

        for (Debt debt : debts) {
            String color;
            if (debt.isPaid()) {
                color = "green";
            } else if (debt.getDateExpiration().isBefore(LocalDate.now())) {
                color = "red";
            } else if (debt.getDateExpiration().isBefore(LocalDate.now().plusDays(7))) {
                color = "yellow";
            } else {
                color = "black";
            }

            DebtResponse debtResponse = new DebtResponse(
                    debt.getNumberDocument(),
                    debt.getCompany(),
                    debt.getAmount(),
                    debt.getDateExpiration(),
                    color
            );

            debtResponses.add(debtResponse);
        }

        return debtResponses;
    }

    @Override
    public DebtResponse markDebtAsPaid(Long debtId) {
        Debt debt = debtRepository.findById(debtId)
                .orElseThrow(() -> new IllegalArgumentException("Deuda no encontrada con id: " + debtId));
        debt.setPaid(true);
        Debt updatedDebt = debtRepository.save(debt);

        return new DebtResponse(
                updatedDebt.getNumberDocument(),
                updatedDebt.getCompany(),
                updatedDebt.getAmount(),
                updatedDebt.getDateExpiration(),
                "green"
        );
    }

    @Override
    public boolean hasDebtsDueToday() {
        LocalDate today = LocalDate.now();
        return debtRepository.existsByDateExpiration(today);
    }
}
