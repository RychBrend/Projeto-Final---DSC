package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "ITEM_PECA")
public class ItemPecaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ordem_de_servico_id", nullable = false)
    private OrdemDeServicoModel ordemDeServico;

    @ManyToOne
    @JoinColumn(name = "peca_id", nullable = false)
    private PecaModel peca;

    @Column(nullable = false)
    private Integer quantidadeUtilizada;

    @Column(nullable = false)
    private BigDecimal precoUnitario;
}
