package com.projetofinal.api.dto.response;

import com.projetofinal.api.entities.Cliente;

import lombok.Data;

@Data
public class AuthResponseDto {
	
	private String message;
	private String acessTokens;
	private Cliente data;

}
