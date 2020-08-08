package br.com.carlosaurelio.crud.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.carlosaurelio.crud.entity.Pessoa;

public interface PessoaRepository extends MongoRepository<Pessoa, String> {
	public Pessoa findByCpf(String cpf);
}
