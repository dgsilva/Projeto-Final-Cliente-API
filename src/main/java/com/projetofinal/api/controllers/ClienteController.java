package com.projetofinal.api.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetofinal.api.dto.request.ClienteDto;
import com.projetofinal.api.dto.response.ClienteResponse;
import com.projetofinal.api.entities.Cliente;
import com.projetofinal.api.services.ClienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api("Cliente")
@RestController
@RequestMapping("/v1/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@ApiOperation("Servico para cadastro de cliente")
	@PostMapping()
	public ResponseEntity<ClienteResponse> save(@Valid @RequestBody ClienteDto dto) {
		ModelMapper modelMapper = new ModelMapper();
	    return clienteService.create(modelMapper.map(dto, Cliente.class));
	}
}
