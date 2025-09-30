package com.example.demo.MODELS;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "USUARIO")
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true) // Campo para login.
    private String username;

    @Column(nullable = false) // Campo para senha.
    private String password;

    private String cargo;
}