package com.example.demo.controller;


import com.example.demo.dto.ServicoRequestDTO;
import com.example.demo.dto.ServicoResponseDTO;
import com.example.demo.service.ServicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicos")
public class ServicoController {

    private final ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @PostMapping
    public ResponseEntity<ServicoResponseDTO> criarServico(@Valid @RequestBody ServicoRequestDTO servicoDTO) { // A anotação @Valid aciona a validação do DTO. [cite: 41]
        ServicoResponseDTO novoServico = servicoService.salvar(servicoDTO);
        return new ResponseEntity<>(novoServico, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ServicoResponseDTO>> listarTodosServicos() {
        List<ServicoResponseDTO> servicos = servicoService.listarTodos();
        return ResponseEntity.ok(servicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoResponseDTO> buscarServicoPorId(@PathVariable Integer id) {
        ServicoResponseDTO servico = servicoService.buscarPorId(id);
        return ResponseEntity.ok(servico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoResponseDTO> atualizarServico(@PathVariable Integer id, @Valid @RequestBody ServicoRequestDTO servicoDTO) {
        ServicoResponseDTO servicoAtualizado = servicoService.atualizar(id, servicoDTO);
        return ResponseEntity.ok(servicoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarServico(@PathVariable Integer id) {
        servicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
