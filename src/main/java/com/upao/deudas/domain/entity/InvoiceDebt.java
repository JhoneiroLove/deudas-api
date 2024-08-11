package com.upao.deudas.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class InvoiceDebt extends Debt {
    private String clientName;
    private String clientAddress;
    private String paymentMethod;
    private Double subtotal;
    private Double igv;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "invoiceDebt")
    private List<InvoiceDetail> invoiceDetails;
}
