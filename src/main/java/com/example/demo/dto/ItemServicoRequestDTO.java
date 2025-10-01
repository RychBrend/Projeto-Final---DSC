package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ItemServicoRequestDTO {
    @NotNull
    private Integer servicoId;

    @NotNull
    @Positive
    private BigDecimal precoCobrado;
}
