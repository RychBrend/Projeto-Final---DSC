package com.example.demo.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ServicoResponseDTO {
    private Integer id;
    private String nome;
    private String descricao;
    private BigDecimal precoPadrao;
}