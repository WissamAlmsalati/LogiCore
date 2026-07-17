package com.example.logicore.controller;

import com.example.logicore.dto.ApiResponse;
import com.example.logicore.dto.ClientRequestDTO;
import com.example.logicore.dto.ClientResponseDTO;
import com.example.logicore.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ApiResponse<ClientResponseDTO>> addClient(@Valid @RequestBody ClientRequestDTO request){
        ClientResponseDTO savedClient = clientService.addClient(request);
        return ResponseEntity.ok(new ApiResponse<>(true,"تم انشاء المستحدم بنجاح"));
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<ClientResponseDTO>>> getAllClients(){
        List<ClientResponseDTO> client = clientService.getAllClient();
        return ResponseEntity.ok(new ApiResponse<>(true,"تم جلب جميع العملاء بنجاح", client));
    }

}
