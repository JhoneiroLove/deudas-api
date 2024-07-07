package com.upao.deudas.service;

import com.upao.deudas.domain.dto.RegisterTaxDebt;
import com.upao.deudas.domain.entity.TaxDebt;

public interface TaxtDebtService {
    public TaxDebt registerTaxDebt(RegisterTaxDebt registerTaxDebt, String token);
}
