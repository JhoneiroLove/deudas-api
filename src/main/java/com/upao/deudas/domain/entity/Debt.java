package com.upao.deudas.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "Debt")
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "debts", uniqueConstraints = {@UniqueConstraint(columnNames = {"numberDocument"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public abstract class Debt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String numberDocument;
    private String company;
    private Double amount;
    private LocalDate dateExpiration;
}
