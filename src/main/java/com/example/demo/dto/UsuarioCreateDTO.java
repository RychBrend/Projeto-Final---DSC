package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioCreateDTO {
    @NotBlank
    private String nome;

    @NotBlank
    @Size(min = 4, message = "Username deve ter no mínimo 4 caracteres")
    private String username;

    @NotBlank
    @Size(min = 6, message = "Password deve ter no mínimo 6 caracteres")
    private String password;

    private String cargo;
}