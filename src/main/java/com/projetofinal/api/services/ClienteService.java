package com.projetofinal.api.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projetofinal.api.dto.request.ClienteDto;
import com.projetofinal.api.dto.response.ClienteResponse;
import com.projetofinal.api.entities.Cliente;
import com.projetofinal.api.repositories.ClienteRepository;

@Transactional
@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public ResponseEntity<ClienteResponse> create(ClienteDto dto) {
		ClienteResponse response = new ClienteResponse();
		
		if (clienteRepository.findByEmail(dto.getEmail()).isPresent()) {
			throw new IllegalArgumentException("O email Já existe, tente outro");
		}

		if (clienteRepository.findByTelefone(dto.getTelefone()).isPresent()) {
			throw new IllegalArgumentException("O telefone já existe, tente outro");
		}
		
		try {
			ModelMapper modelMapper = new ModelMapper();
			Cliente cliente = clienteRepository.save(modelMapper.map(dto, Cliente.class));
			response.setMensagem("Cadastrado com sucesso!");
			response.setData(cliente);
			
		} catch (Exception e) {
			response.setMensagem(e.getMessage());
			ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
		
		
	}
}
