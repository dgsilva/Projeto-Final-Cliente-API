package com.projetofinal.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetofinal.api.dto.request.AuthRequestDto;
import com.projetofinal.api.dto.response.AuthResponseDto;
import com.projetofinal.api.services.ClienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("Autenticação")
@RestController
@RequestMapping("/v1/login")
public class AuthController {

	@Autowired
	private ClienteService clienteService;
	
	@ApiOperation("Servico para autenticação do cliente")
	@PostMapping
	public ResponseEntity<AuthResponseDto> logar(@Valid @RequestBody AuthRequestDto dto){
		return clienteService.logar(dto);
	}
	
	
}
