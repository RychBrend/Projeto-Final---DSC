package com.example.demo.controller;
;
import com.example.demo.dto.VeiculoRequestDTO;
import com.example.demo.dto.VeiculoResponseDTO;
import com.example.demo.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes/{clienteId}/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @PostMapping
    public ResponseEntity<VeiculoResponseDTO> criarVeiculo(
            @PathVariable Integer clienteId,
            @Valid @RequestBody VeiculoRequestDTO veiculoDTO
    ) {

        VeiculoResponseDTO novoVeiculo = veiculoService.salvar(clienteId, veiculoDTO);
        return new ResponseEntity<>(novoVeiculo, HttpStatus.CREATED);
    }
}