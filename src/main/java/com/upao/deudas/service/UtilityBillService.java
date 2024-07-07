package com.upao.deudas.service;

import com.upao.deudas.domain.dto.RegisterUtilityBill;
import com.upao.deudas.domain.entity.UtilityBill;

public interface UtilityBillService {
    public UtilityBill registerUtilityBill(RegisterUtilityBill registerUtilityBill, String token);
}
