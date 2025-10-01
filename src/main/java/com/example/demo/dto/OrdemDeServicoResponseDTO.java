package com.example.demo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrdemDeServicoResponseDTO {
    private Integer id;
    private LocalDateTime dataEntrada;
    private LocalDateTime dataConclusaoPrevista;
    private String status;
    private String descricaoProblema;
    private BigDecimal valorTotal;

    private ClienteResponseDTO cliente;
    private VeiculoResponseDTO veiculo;


    private List<ItemServicoResponseDTO> servicosRealizados;
    private List<ItemPecaResponseDTO> pecasUtilizadas;
}


@Data
class ItemServicoResponseDTO {
    private Integer id;
    private String servicoNome;
    private BigDecimal precoCobrado;
}

@Data
class ItemPecaResponseDTO {
    private Integer id;
    private String pecaNome;
    private Integer quantidadeUtilizada;
    private BigDecimal precoUnitario;
    private BigDecimal precoTotal;
}
