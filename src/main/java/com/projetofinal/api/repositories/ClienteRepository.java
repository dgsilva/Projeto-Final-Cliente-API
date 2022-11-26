package com.projetofinal.api.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.projetofinal.api.entities.Cliente;
@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String> {

	@Query("{email: ?0}")
	Optional<Cliente> findByEmail(String email);
	
	@Query("{telefone: ?0}")
	Optional <Cliente> findByTelefone(String telefone);
	
	@Query("{email: ?0, senha: ?1}")
	Optional<Cliente> findyByEmailAndSenha(String email, String senha);
}
