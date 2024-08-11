package com.upao.deudas.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "Debt")
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "debts", uniqueConstraints = {@UniqueConstraint(columnNames = {"number_document", "user_id"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public abstract class Debt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number_document")
    private String numberDocument;
    private String company;
    private Double amount;
    private LocalDate dateExpiration;
    private boolean isPaid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
