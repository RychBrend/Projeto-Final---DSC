package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "VEICULO")
public class VeiculoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String placa;
    private String marca;
    private String modelo;
    private Integer anoFabricacao;

    @ManyToOne // Muitos veículos pertencem a um cliente
    @JoinColumn(name = "cliente_id", nullable = false) // Chave estrangeira
    @JsonBackReference // <-- ADICIONADO AQUI
    private ClienteModel cliente;
}