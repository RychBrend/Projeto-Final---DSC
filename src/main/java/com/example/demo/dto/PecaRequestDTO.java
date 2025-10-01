package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class PecaRequestDTO {

    @NotBlank(message = "O nome da peça não pode ser vazio.")
    private String nome;

    private String fabricante;

    @NotNull(message = "O preço não pode ser nulo.")
    @PositiveOrZero(message = "O preço não pode ser negativo.")
    private BigDecimal preco;

    @NotNull(message = "A quantidade em estoque não pode ser nula.")
    @PositiveOrZero(message = "A quantidade em estoque não pode ser negativa.")
    private Integer quantidadeEstoque;
}