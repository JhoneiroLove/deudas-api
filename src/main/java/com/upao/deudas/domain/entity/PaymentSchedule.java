package com.upao.deudas.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class PaymentSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer paymentNumber;
    private LocalDate dueDate;
    private Double balance;
    private Double principal;
    private Double interest;
    private Double installment;

    @ManyToOne
    @JoinColumn(name = "loan_debt_id")
    private LoanDebt loanDebt;
}
