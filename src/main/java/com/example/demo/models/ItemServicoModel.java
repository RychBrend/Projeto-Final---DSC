package com.example.demo.models;


import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "ITEM_SERVICO")
public class ItemServicoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ordem_de_servico_id", nullable = false)
    private OrdemDeServicoModel ordemDeServico;

    @ManyToOne
    @JoinColumn(name = "servico_id", nullable = false)
    private ServicoModel servico;

    @Column(nullable = false)
    private BigDecimal precoCobrado;
}