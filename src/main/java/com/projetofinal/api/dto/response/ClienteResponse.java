package com.projetofinal.api.dto.response;

import com.projetofinal.api.entities.Cliente;

import lombok.Data;

@Data
public class ClienteResponse {

	private String mensagem;
	private Cliente data;
}
