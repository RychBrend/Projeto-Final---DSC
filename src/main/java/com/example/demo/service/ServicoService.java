package com.example.demo.service;


import com.example.demo.dto.ServicoRequestDTO;
import com.example.demo.dto.ServicoResponseDTO;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.models.ServicoModel;
import com.example.demo.repository.ServicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    @Transactional
    public ServicoResponseDTO salvar(ServicoRequestDTO servicoDTO) {
        ServicoModel servico = convertToEntity(servicoDTO);
        ServicoModel servicoSalvo = servicoRepository.save(servico);
        return convertToResponseDto(servicoSalvo);
    }

    @Transactional(readOnly = true)
    public List<ServicoResponseDTO> listarTodos() {
        return servicoRepository.findAll().stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ServicoResponseDTO buscarPorId(Integer id) {
        ServicoModel servico = servicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado com o id: " + id));
        return convertToResponseDto(servico);
    }

    @Transactional
    public ServicoResponseDTO atualizar(Integer id, ServicoRequestDTO servicoDTO) {
        ServicoModel servicoExistente = servicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado com o id: " + id));

        servicoExistente.setNome(servicoDTO.getNome());
        servicoExistente.setDescricao(servicoDTO.getDescricao());
        servicoExistente.setPrecoPadrao(servicoDTO.getPrecoPadrao());

        ServicoModel servicoAtualizado = servicoRepository.save(servicoExistente);
        return convertToResponseDto(servicoAtualizado);
    }

    @Transactional
    public void deletar(Integer id) {
        if (!servicoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Serviço não encontrado com o id: " + id);
        }
        servicoRepository.deleteById(id);
    }

    // Métodos auxiliares para conversão
    private ServicoModel convertToEntity(ServicoRequestDTO dto) {
        ServicoModel servico = new ServicoModel();
        servico.setNome(dto.getNome());
        servico.setDescricao(dto.getDescricao());
        servico.setPrecoPadrao(dto.getPrecoPadrao());
        return servico;
    }

    private ServicoResponseDTO convertToResponseDto(ServicoModel servico) {
        ServicoResponseDTO dto = new ServicoResponseDTO();
        dto.setId(servico.getId());
        dto.setNome(servico.getNome());
        dto.setDescricao(servico.getDescricao());
        dto.setPrecoPadrao(servico.getPrecoPadrao());
        return dto;
    }
}