package com.upao.deudas.service;

import com.upao.deudas.domain.dto.RegisterLoanDebt;
import com.upao.deudas.domain.entity.LoanDebt;

public interface LoanDebtService {
    public LoanDebt registerLoanDebt(RegisterLoanDebt registerLoanDebt, String token);
}
