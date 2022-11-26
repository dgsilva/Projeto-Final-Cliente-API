package com.projetofinal.api.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AuthRequestDto {
	
	@Email(message = "Email invalido, por favor preencher o email")
	@NotBlank(message = "Email dp cliente é obrigatorio.")
	private String email;
	@Size(min = 8, max = 20, message = "Senha do cliente é obrigatorio")
	@NotBlank(message = "Senha do cliente é obrigatoria.")
	private String senha;

}
