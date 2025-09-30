package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ClienteRequestDTO {

    @NotBlank(message = "O nome não pode ser vazio.") // Validação para não ser nulo nem vazio.
    private String nome;

    @NotBlank(message = "O CPF não pode ser vazio.")
    @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos.") // Validação com expressão regular
    private String cpf;

    private String telefone;

    @Email(message = "Formato de e-mail inválido.") // Validação de e-mail. [cite: 853]
    private String email;

    @NotBlank(message = "O endereço não pode ser vazio.")
    private String endereco;
}