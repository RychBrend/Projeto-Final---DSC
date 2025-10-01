package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VeiculoRequestDTO {

    @NotBlank(message = "A placa não pode ser vazia.")
    private String placa;

    @NotBlank(message = "A marca não pode ser vazia.")
    private String marca;

    @NotBlank(message = "O modelo não pode ser vazio.")
    private String modelo;

    @NotNull(message = "O ano de fabricação não pode ser nulo.")
    private Integer anoFabricacao;
}