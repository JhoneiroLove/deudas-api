package com.upao.deudas.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class InvoiceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private String product;
    private Double price;
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "invoice_debt_id")
    private InvoiceDebt invoiceDebt;
}
