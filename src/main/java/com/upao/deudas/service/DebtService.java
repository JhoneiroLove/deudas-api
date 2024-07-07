package com.upao.deudas.service;

import com.upao.deudas.domain.dto.AlertDueTodayResponse;
import com.upao.deudas.domain.dto.DebtResponse;

import java.util.List;

public interface DebtService {
    public List<DebtResponse> getDebtsByMonth(int year, int month);
    public DebtResponse markDebtAsPaid(Long debtId);
    public AlertDueTodayResponse getDebtsDueToday();
}
