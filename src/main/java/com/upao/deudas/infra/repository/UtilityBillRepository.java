package com.upao.deudas.infra.repository;

import com.upao.deudas.domain.entity.User;
import com.upao.deudas.domain.entity.UtilityBill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilityBillRepository extends JpaRepository<UtilityBill, Long> {
    boolean existsByNumberDocumentAndUser(String numberDocument, User user);
}
