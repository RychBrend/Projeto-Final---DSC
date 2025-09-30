package com.example.demo.dto;

import lombok.Data;

@Data
public class ClienteResponseDTO {
    private Integer id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String endereco;
}