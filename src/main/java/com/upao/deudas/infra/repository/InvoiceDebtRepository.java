package com.upao.deudas.infra.repository;

import com.upao.deudas.domain.entity.InvoiceDebt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceDebtRepository extends JpaRepository<InvoiceDebt, Long> {
}
