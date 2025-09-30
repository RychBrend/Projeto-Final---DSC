package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "PECA")
public class PecaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;
    private String fabricante;

    @Column(nullable = false)
    private BigDecimal preco;
    private Integer quantidadeEstoque;
}