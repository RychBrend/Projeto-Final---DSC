package com.example.demo.controller;

import com.example.demo.dto.PecaRequestDTO;
import com.example.demo.dto.PecaResponseDTO;
import com.example.demo.service.PecaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pecas")
public class PecaController {

    private final PecaService pecaService;

    public PecaController(PecaService pecaService) {
        this.pecaService = pecaService;
    }

    @PostMapping
    public ResponseEntity<PecaResponseDTO> criarPeca(@Valid @RequestBody PecaRequestDTO pecaDTO) {
        PecaResponseDTO novaPeca = pecaService.salvar(pecaDTO);
        return new ResponseEntity<>(novaPeca, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PecaResponseDTO>> listarTodasPecas() {
        List<PecaResponseDTO> pecas = pecaService.listarTodas();
        return ResponseEntity.ok(pecas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PecaResponseDTO> buscarPecaPorId(@PathVariable Integer id) {
        PecaResponseDTO peca = pecaService.buscarPorId(id);
        return ResponseEntity.ok(peca);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PecaResponseDTO> atualizarPeca(@PathVariable Integer id, @Valid @RequestBody PecaRequestDTO pecaDTO) {
        PecaResponseDTO pecaAtualizada = pecaService.atualizar(id, pecaDTO);
        return ResponseEntity.ok(pecaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPeca(@PathVariable Integer id) {
        pecaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}