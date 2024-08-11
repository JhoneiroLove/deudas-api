package com.upao.deudas.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TaxDebt extends Debt {
    private String period;
    private String name;
    private String address;
}