package com.upao.deudas.service.impl;

import com.upao.deudas.domain.dto.DebtResponse;
import com.upao.deudas.domain.entity.Debt;
import com.upao.deudas.infra.repository.DebtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DebtServiceImpl {
    private final DebtRepository debtRepository;

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
}
