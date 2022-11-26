package com.projetofinal.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetofinal.api.dto.request.ClienteDto;
import com.projetofinal.api.dto.response.ClienteResponse;
import com.projetofinal.api.services.ClienteService;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@PostMapping()
	public ResponseEntity<ClienteResponse> save(@Valid @RequestBody ClienteDto dto) {
	    return clienteService.create(dto);
	}
}
