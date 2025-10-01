package com.example.demo.service;


import com.example.demo.dto.ItemServicoRequestDTO;
import com.example.demo.dto.OrdemDeServicoRequestDTO;
import com.example.demo.dto.OrdemDeServicoResponseDTO;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.models.*;
import com.example.demo.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrdemDeServicoService {

    private final OrdemDeServicoRepository osRepository;
    private final ClienteRepository clienteRepository;
    private final VeiculoRepository veiculoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ServicoRepository servicoRepository;
    private final PecaRepository pecaRepository;

    public OrdemDeServicoService(OrdemDeServicoRepository osRepository, ClienteRepository clienteRepository, VeiculoRepository veiculoRepository, UsuarioRepository usuarioRepository, ServicoRepository servicoRepository, PecaRepository pecaRepository) {
        this.osRepository = osRepository;
        this.clienteRepository = clienteRepository;
        this.veiculoRepository = veiculoRepository;
        this.usuarioRepository = usuarioRepository;
        this.servicoRepository = servicoRepository;
        this.pecaRepository = pecaRepository;
    }

    @Transactional
    public OrdemDeServicoResponseDTO salvar(OrdemDeServicoRequestDTO osDTO) {
        ClienteModel cliente = clienteRepository.findById(osDTO.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado."));
        VeiculoModel veiculo = veiculoRepository.findById(osDTO.getVeiculoId())
                .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado."));

        UsuarioModel mecanico = null;
        if (osDTO.getMecanicoId() != null) {
            mecanico = usuarioRepository.findById(osDTO.getMecanicoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Mecânico não encontrado."));
        }


        OrdemDeServicoModel os = new OrdemDeServicoModel();
        os.setCliente(cliente);
        os.setVeiculo(veiculo);
        os.setMecanico(mecanico);
        os.setDescricaoProblema(osDTO.getDescricaoProblema());
        os.setDataEntrada(osDTO.getDataEntrada() != null ? osDTO.getDataEntrada() : java.time.LocalDateTime.now());
        os.setStatus(osDTO.getStatus() != null ? osDTO.getStatus() : "ABERTA");


        List<ItemServicoModel> servicos = new ArrayList<>();
        if (osDTO.getServicosRealizados() != null) {
            for (ItemServicoRequestDTO itemDTO : osDTO.getServicosRealizados()) {
                ServicoModel servico = servicoRepository.findById(itemDTO.getServicoId())
                        .orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado: " + itemDTO.getServicoId()));
                ItemServicoModel item = new ItemServicoModel();
                item.setServico(servico);
                item.setPrecoCobrado(itemDTO.getPrecoCobrado());
                item.setOrdemDeServico(os);
                servicos.add(item);
            }
        }
        os.setServicosRealizados(servicos);


        BigDecimal valorTotal = BigDecimal.ZERO;
        valorTotal = valorTotal.add(servicos.stream().map(ItemServicoModel::getPrecoCobrado).reduce(BigDecimal.ZERO, BigDecimal::add));

        os.setValorTotal(valorTotal);

        OrdemDeServicoModel osSalva = osRepository.save(os);

        return convertToResponseDto(osSalva);
    }


    private OrdemDeServicoResponseDTO convertToResponseDto(OrdemDeServicoModel os) {
        return new OrdemDeServicoResponseDTO();
    }
}
