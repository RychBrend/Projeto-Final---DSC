package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CLIENTE")
public class ClienteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Estratégia de auto-incremento
    private Integer id;

    @Column(nullable = false) // @Column para mapear atributos
    private String nome;

    @Column(unique = true, nullable = false)
    private String cpf;

    private String telefone;
    private String email;
    private String endereco;

    @OneToMany(mappedBy = "cliente") // Um cliente pode ter muitos veículos
    @JsonManagedReference // <-- ADICIONADO AQUI
    private List<VeiculoModel> veiculos;
}