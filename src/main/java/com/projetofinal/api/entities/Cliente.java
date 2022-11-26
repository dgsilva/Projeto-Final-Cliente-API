package com.projetofinal.api.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Document(collection = "cliente")
public class Cliente {

	@Id
	private String _idCliente;

	private String nome;
	
	@Indexed(unique = true)
	private String telefone;
	
	@Indexed(unique = true)
	private String email;
	
	@JsonIgnore
	private String senha;

}
