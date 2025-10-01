package com.example.demo.service;


import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.models.ClienteModel;
import com.example.demo.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Indica que é um componente de serviço. [cite: 305]
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteModel> findAll() {
        return clienteRepository.findAll();
    }

    public ClienteModel findById(Integer id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com o id: " + id)); // Lança exceção customizada se o cliente não for encontrado. [cite: 154]
    }

    public ClienteModel save(ClienteModel cliente) {
        // Poderia adicionar uma lógica para verificar se o CPF já existe
        return clienteRepository.save(cliente);
    }

    public void deleteById(Integer id) {
        if (!clienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente não encontrado com o id: " + id);
        }
        clienteRepository.deleteById(id);
    }
}

