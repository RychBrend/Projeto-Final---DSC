package com.example.demo.service;

import com.example.demo.dto.PecaRequestDTO;
import com.example.demo.dto.PecaResponseDTO;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.models.PecaModel;
import com.example.demo.repository.PecaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PecaService {

    private final PecaRepository pecaRepository;

    public PecaService(PecaRepository pecaRepository) {
        this.pecaRepository = pecaRepository;
    }

    @Transactional
    public PecaResponseDTO salvar(PecaRequestDTO pecaDTO) {
        PecaModel peca = convertToEntity(pecaDTO);
        PecaModel pecaSalva = pecaRepository.save(peca);
        return convertToResponseDto(pecaSalva);
    }

    @Transactional
    public List<PecaResponseDTO> listarTodas() {
        return pecaRepository.findAll().stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public PecaResponseDTO buscarPorId(Integer id) {
        PecaModel peca = pecaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Peça não encontrada com o id: " + id));
        return convertToResponseDto(peca);
    }

    @Transactional
    public PecaResponseDTO atualizar(Integer id, PecaRequestDTO pecaDTO) {
        PecaModel pecaExistente = pecaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Peça não encontrada com o id: " + id));

        pecaExistente.setNome(pecaDTO.getNome());
        pecaExistente.setFabricante(pecaDTO.getFabricante());
        pecaExistente.setPreco(pecaDTO.getPreco());
        pecaExistente.setQuantidadeEstoque(pecaDTO.getQuantidadeEstoque());

        PecaModel pecaAtualizada = pecaRepository.save(pecaExistente);
        return convertToResponseDto(pecaAtualizada);
    }

    @Transactional
    public void deletar(Integer id) {
        if (!pecaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Peça não encontrada com o id: " + id);
        }
        pecaRepository.deleteById(id);
    }

    // Métodos de conversão
    private PecaModel convertToEntity(PecaRequestDTO dto) {
        PecaModel peca = new PecaModel();
        peca.setNome(dto.getNome());
        peca.setFabricante(dto.getFabricante());
        peca.setPreco(dto.getPreco());
        peca.setQuantidadeEstoque(dto.getQuantidadeEstoque());
        return peca;
    }

    private PecaResponseDTO convertToResponseDto(PecaModel peca) {
        PecaResponseDTO dto = new PecaResponseDTO();
        dto.setId(peca.getId());
        dto.setNome(peca.getNome());
        dto.setFabricante(peca.getFabricante());
        dto.setPreco(peca.getPreco());
        dto.setQuantidadeEstoque(peca.getQuantidadeEstoque());
        return dto;
    }
}
