package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ItemPecaRequestDTO {
    @NotNull
    private Integer pecaId;

    @NotNull
    @Positive
    private Integer quantidadeUtilizada;

    @NotNull
    @Positive
    private BigDecimal precoUnitario;
}