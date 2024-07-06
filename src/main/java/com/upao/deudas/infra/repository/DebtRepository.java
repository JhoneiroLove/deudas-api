package com.upao.deudas.infra.repository;

import com.upao.deudas.domain.entity.Debt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DebtRepository extends JpaRepository<Debt, Long> {
    Optional<Debt> findByNumberDocument(String numeroDocumento);
}
