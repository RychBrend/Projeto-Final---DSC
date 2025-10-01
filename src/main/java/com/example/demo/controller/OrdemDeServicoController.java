package com.example.demo.controller;

import com.example.demo.dto.OrdemDeServicoRequestDTO;
import com.example.demo.dto.OrdemDeServicoResponseDTO;
import com.example.demo.service.OrdemDeServicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ordens-de-servico")
public class OrdemDeServicoController {

    private final OrdemDeServicoService osService;

    public OrdemDeServicoController(OrdemDeServicoService osService) {
        this.osService = osService;
    }

    @PostMapping
    public ResponseEntity<OrdemDeServicoResponseDTO> criarOrdemDeServico(@Valid @RequestBody OrdemDeServicoRequestDTO osDTO) {
        OrdemDeServicoResponseDTO novaOS = osService.salvar(osDTO);
        return new ResponseEntity<>(novaOS, HttpStatus.CREATED);
    }


}
