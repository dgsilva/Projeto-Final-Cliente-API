package com.projetofinal.api.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ClienteDto {

	@Size(min = 6, max = 150, message = "Tem ser um digito de min 6 a 150 caracteres.")
	@NotBlank(message = "Nome do cliente é obrigatorio.")
	private String nome;

	@Email(message = "Email invalido, por favor preencher o email")
	@NotBlank(message = "Email do cliente é obrigatorio.")
	private String email;

	@Pattern(regexp = "(^$|[0-9]{11})", message = "Telefone deve ter 11 digitos numéricos")
	@NotBlank(message = "Telefone do cliente é obrigatorio.")
	private String telefone;

	@Size(min = 8, max = 20, message = "Senha do cliente é obrigatorio")
	@NotBlank(message = "Senha do cliente é obrigatoria.")
	private String senha;
}
