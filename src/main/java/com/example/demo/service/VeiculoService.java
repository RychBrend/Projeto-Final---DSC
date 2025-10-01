package com.example.demo.service;


import com.example.demo.dto.VeiculoRequestDTO;
import com.example.demo.dto.VeiculoResponseDTO;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.models.ClienteModel;
import com.example.demo.models.VeiculoModel;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.VeiculoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final ClienteRepository clienteRepository;

    // Injetando os repositórios necessários no construtor. [cite: 1234]
    public VeiculoService(VeiculoRepository veiculoRepository, ClienteRepository clienteRepository) {
        this.veiculoRepository = veiculoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional // Garante que a operação seja atômica
    public VeiculoResponseDTO salvar(Integer clienteId, VeiculoRequestDTO veiculoDTO) {
        // 1. Busca o cliente pelo ID ou lança uma exceção se não encontrar. [cite: 326]
        ClienteModel cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com o id: " + clienteId));

        // 2. Converte o DTO para a entidade Veiculo
        VeiculoModel veiculo = convertToEntity(veiculoDTO);

        // 3. Associa o veículo ao cliente encontrado
        veiculo.setCliente(cliente);

        // 4. Salva o novo veículo no banco de dados
        VeiculoModel veiculoSalvo = veiculoRepository.save(veiculo);

        // 5. Converte a entidade salva para um DTO de resposta e retorna
        return convertToResponseDto(veiculoSalvo);
    }

    // Métodos privados para auxiliar na conversão entre DTO e Entidade
    private VeiculoModel convertToEntity(VeiculoRequestDTO dto) {
        VeiculoModel veiculo = new VeiculoModel();
        veiculo.setPlaca(dto.getPlaca());
        veiculo.setMarca(dto.getMarca());
        veiculo.setModelo(dto.getModelo());
        veiculo.setAnoFabricacao(dto.getAnoFabricacao());
        return veiculo;
    }

    private VeiculoResponseDTO convertToResponseDto(VeiculoModel veiculo) {
        VeiculoResponseDTO dto = new VeiculoResponseDTO();
        dto.setId(veiculo.getId());
        dto.setPlaca(veiculo.getPlaca());
        dto.setMarca(veiculo.getMarca());
        dto.setModelo(veiculo.getModelo());
        dto.setAnoFabricacao(veiculo.getAnoFabricacao());
        dto.setClienteId(veiculo.getCliente().getId());
        return dto;
    }
}