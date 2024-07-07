package com.upao.deudas.infra.repository;

import com.upao.deudas.domain.entity.TaxDebt;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TaxDebtRepository extends JpaRepository<TaxDebt, Long> {
    Optional<TaxDebt> findByNumberDocument(String numberDocument);
}
