package com.example.logicore.service;

import com.example.logicore.dto.ClientRequestDTO;
import com.example.logicore.dto.ClientResponseDTO;
import com.example.logicore.entity.Client;
import com.example.logicore.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {



    private final ClientRepository clientRepository;
    private ClientResponseDTO mapToDTO(Client client) {
        ClientResponseDTO dto = new ClientResponseDTO();
        dto.setFirstName(client.getFirstName());
        dto.setLastName(client.getLastName());
        dto.setPhoneNumber(client.getPhoneNumber());
        dto.setClientCode(client.getClientCode());
        return dto;
    }

    public ClientResponseDTO addClient(ClientRequestDTO request){
        if (clientRepository.existsByPhoneNumber(request.getPhoneNumber())){
            throw new IllegalArgumentException("رقم الهاتف مسجل مسبقا");
        }

        Client client = new Client();
        client.setFirstName(request.getFirstName());
        client.setLastName(request.getLastName());
        client.setPhoneNumber(request.getPhoneNumber());
        client.setClientCode("C" + System.currentTimeMillis() + UUID.randomUUID().toString().substring(0,8).toUpperCase() );


        Client saveclient = clientRepository.save(client);

        return mapToDTO(saveclient);
    }

    public List<ClientResponseDTO> getAllClient(){
        return clientRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

}
