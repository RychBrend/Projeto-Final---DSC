package com.example.demo.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PecaResponseDTO {
    private Integer id;
    private String nome;
    private String fabricante;
    private BigDecimal preco;
    private Integer quantidadeEstoque;
}
