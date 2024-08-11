package com.upao.deudas.infra.repository;

import com.upao.deudas.domain.entity.TaxDebt;
import com.upao.deudas.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxDebtRepository extends JpaRepository<TaxDebt, Long> {
    boolean existsByNumberDocumentAndUser(String numberDocument, User user);
}
