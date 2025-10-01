package com.example.demo.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrdemDeServicoRequestDTO {
    @NotNull
    private Integer clienteId;

    @NotNull
    private Integer veiculoId;

    private Integer mecanicoId;

    @NotBlank
    private String descricaoProblema;

    private LocalDateTime dataEntrada;
    private String status;

    @Valid
    private List<ItemServicoRequestDTO> servicosRealizados;

    @Valid
    private List<ItemPecaRequestDTO> pecasUtilizadas;
}
