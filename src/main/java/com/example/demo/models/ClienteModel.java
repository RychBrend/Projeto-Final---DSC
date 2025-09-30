package com.example.demo.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CLIENTE") // Mapeia para a tabela CLIENTE
@Getter
@Setter
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
    private List<VeiculoModel> veiculos;
}