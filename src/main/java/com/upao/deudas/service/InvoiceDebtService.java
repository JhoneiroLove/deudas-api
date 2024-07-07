package com.upao.deudas.service;

import com.upao.deudas.domain.dto.RegisterInvoiceDebt;
import com.upao.deudas.domain.entity.InvoiceDebt;

public interface InvoiceDebtService {
    public InvoiceDebt registerInvoiceDebt(RegisterInvoiceDebt registerInvoiceDebt, String token);
}
