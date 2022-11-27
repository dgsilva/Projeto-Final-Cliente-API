package com.projetofinal.api.services;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projetofinal.api.dto.request.AuthRequestDto;
import com.projetofinal.api.dto.response.AuthResponseDto;
import com.projetofinal.api.dto.response.ClienteResponse;
import com.projetofinal.api.entities.Cliente;
import com.projetofinal.api.repositories.ClienteRepository;
import com.projetofinal.api.security.TokenAuthenticationService;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private TokenAuthenticationService tokenAuthenticationService;

	public ResponseEntity<ClienteResponse> create(Cliente cliente) {
		ClienteResponse response = new ClienteResponse();

		if (clienteRepository.findByEmail(cliente.getEmail()).isPresent()) {
			throw new IllegalArgumentException("O email Já existe, tente outro");
		}

		if (clienteRepository.findByTelefone(cliente.getTelefone()).isPresent()) {
			throw new IllegalArgumentException("O telefone já existe, tente outro");
		}
		try {
			cliente.setSenha(getHashMd5(cliente.getSenha()));
			Cliente client = clienteRepository.save(cliente);
			response.setMensagem("Cadastrado com sucesso!");
			response.setData(client);

		} catch (Exception e) {
			response.setMensagem(e.getMessage());
			ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	public ResponseEntity<AuthResponseDto> logar(AuthRequestDto auth) {
		AuthResponseDto authResponse = new AuthResponseDto();
		HttpStatus status = null;
		try {

			Cliente cliente = get(auth.getEmail(), auth.getSenha());

			authResponse.setMessage("Cliente obtido com sucesso.");
			authResponse.setAcessTokens(tokenAuthenticationService.generateToken(cliente.getEmail(), "ROLE_CLIENTE"));
			authResponse.setData(cliente);

			status = HttpStatus.OK;

		} catch (IllegalArgumentException e) {
			authResponse.setMessage(e.getMessage());
			status = HttpStatus.UNAUTHORIZED;
		}
		
		return ResponseEntity.status(status).body(authResponse);
	}

	public Cliente get(String email, String senha) {

		Optional<Cliente> optional = clienteRepository.findByEmailAndSenha(email, getHashMd5(senha));

		if (optional.isEmpty())
			throw new IllegalArgumentException("Dados inválidos, clientes não encontrados");
		Cliente cliente = optional.get();
		return cliente;
	}

	private static String getHashMd5(String value) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		BigInteger hash = new BigInteger(1, md.digest(value.getBytes()));
		return hash.toString(16);
	}
}
