package com.example.demo.dto;

import lombok.Data;

@Data
public class VeiculoResponseDTO {
    private Integer id;
    private String placa;
    private String marca;
    private String modelo;
    private Integer anoFabricacao;
    private Integer clienteId; // Incluindo o ID do cliente para referência
}