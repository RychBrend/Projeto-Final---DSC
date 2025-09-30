package com.example.demo.repository;


import com.example.demo.models.OrdemDeServicoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdemDeServicoRepository extends JpaRepository<OrdemDeServicoModel, Integer> {
}