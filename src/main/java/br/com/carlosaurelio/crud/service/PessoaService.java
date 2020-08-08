package br.com.carlosaurelio.crud.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.carlosaurelio.crud.entity.Pessoa;
import br.com.carlosaurelio.crud.exception.CpfException;
import br.com.carlosaurelio.crud.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;

	public Pessoa create(Pessoa newPessoa) throws CpfException {
		Pessoa pessoa = repository.findByCpf(newPessoa.getCpf());

		if (pessoa != null) throw new CpfException("CPF já existente.");

		newPessoa.setCreateAt(LocalDateTime.now());

		return repository.save(newPessoa);
	}

	public List<Pessoa> getAll() {
		return repository.findAll();
	}

	public Pessoa findById(String id) {
		Optional<Pessoa> pessoa = repository.findById(id);

		if (pessoa.isPresent())
			return pessoa.get();

		return null;
	}

	public Pessoa update(String id, Pessoa newPessoa) {
		Pessoa pessoa = this.findById(id);

		if (pessoa == null) return null;

		pessoa.setNome(newPessoa.getNome());
		pessoa.setSexo(newPessoa.getSexo());
		pessoa.setEmail(newPessoa.getEmail());
		pessoa.setDataNascimento(newPessoa.getDataNascimento());
		pessoa.setNaturalidade(newPessoa.getNaturalidade());
		pessoa.setNacionalidade(newPessoa.getNacionalidade());
		pessoa.setUpdateAt(LocalDateTime.now());

		return repository.save(pessoa);
	}

	public void delete(String id) {
		repository.deleteById(id);
	}
}
