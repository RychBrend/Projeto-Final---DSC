package com.example.demo.repository;


import com.example.demo.models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Integer> { // Estende JpaRepository para herdar métodos CRUD. [cite: 293]
    Optional<ClienteModel> findByCpf(String cpf); // Método de consulta derivado pelo nome. [cite: 173]
}