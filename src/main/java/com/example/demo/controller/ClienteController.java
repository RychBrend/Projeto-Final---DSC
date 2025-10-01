package com.example.demo.controller;


import com.example.demo.dto.ClienteRequestDTO;
import com.example.demo.dto.ClienteResponseDTO;
import com.example.demo.models.ClienteModel;
import com.example.demo.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    // Em um projeto real, injete um Mapper (ex: ModelMapper, MapStruct) para as conversões.

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarTodos() {
        List<ClienteModel> clientes = clienteService.findAll();
        List<ClienteResponseDTO> dtos = clientes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}") // Uso de Path Variable.
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable Integer id) {
        ClienteModel cliente = clienteService.findById(id);
        return ResponseEntity.ok(convertToDto(cliente));
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criar(@Valid @RequestBody ClienteRequestDTO requestDTO) { // A anotação @Valid aciona a validação dos DTOs. [cite: 867]
        ClienteModel cliente = convertToEntity(requestDTO);
        ClienteModel clienteSalvo = clienteService.save(cliente);
        return new ResponseEntity<>(convertToDto(clienteSalvo), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Métodos auxiliares para conversão (idealmente em uma classe separada)
    private ClienteResponseDTO convertToDto(ClienteModel cliente) {
        ClienteResponseDTO dto = new ClienteResponseDTO();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setCpf(cliente.getCpf());
        dto.setEmail(cliente.getEmail());
        dto.setTelefone(cliente.getTelefone());
        dto.setEndereco(cliente.getEndereco());
        return dto;
    }

    private ClienteModel convertToEntity(ClienteRequestDTO dto) {
        ClienteModel cliente = new ClienteModel();
        cliente.setNome(dto.getNome());
        cliente.setCpf(dto.getCpf());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefone(dto.getTelefone());
        cliente.setEndereco(dto.getEndereco());
        return cliente;
    }
}