package com.upao.deudas.infra.repository;

import com.upao.deudas.domain.entity.Debt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DebtRepository extends JpaRepository<Debt, Long> {
    List<Debt> findAllByDateExpirationBetween(LocalDate startDate, LocalDate endDate);
    List<Debt> findAllByDateExpiration(LocalDate date);
}
