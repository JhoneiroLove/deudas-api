package com.upao.deudas.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UtilityBill extends Debt {
    private String clientName;
    private String clientAddress;
}
