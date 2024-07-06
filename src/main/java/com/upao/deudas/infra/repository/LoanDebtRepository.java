package com.upao.deudas.infra.repository;

import com.upao.deudas.domain.entity.LoanDebt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanDebtRepository extends JpaRepository<LoanDebt, Long> {
}
