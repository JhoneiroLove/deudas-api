package com.upao.deudas.infra.repository;

import com.upao.deudas.domain.entity.LoanDebt;
import com.upao.deudas.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanDebtRepository extends JpaRepository<LoanDebt, Long> {
    boolean existsByNumberDocumentAndUser(String numberDocument, User user);
}

