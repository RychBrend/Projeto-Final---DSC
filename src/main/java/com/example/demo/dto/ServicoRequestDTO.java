package com.example.demo.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ServicoRequestDTO {

    @NotBlank(message = "O nome do serviço não pode ser vazio.")
    @Size(min = 3, max = 100, message = "O nome do serviço deve ter entre 3 e 100 caracteres.")
    private String nome;

    private String descricao;

    @NotNull(message = "O preço padrão não pode ser nulo.")
    @Positive(message = "O preço padrão deve ser um valor positivo.")
    private BigDecimal precoPadrao;
}