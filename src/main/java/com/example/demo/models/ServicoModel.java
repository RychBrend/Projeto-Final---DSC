package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "SERVICO")
public class ServicoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;
    private String descricao;

    @Column(nullable = false)
    private BigDecimal precoPadrao;
}
