package br.com.carlosaurelio.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.carlosaurelio.crud.dto.PessoaToCreateDto;
import br.com.carlosaurelio.crud.dto.PessoaToUpdateDto;
import br.com.carlosaurelio.crud.entity.Pessoa;
import br.com.carlosaurelio.crud.exception.CpfException;
import br.com.carlosaurelio.crud.repository.PessoaRepository;

@Service
public class PessoaService {

	private final PessoaRepository repository;

	@Autowired
	public PessoaService(PessoaRepository repository) {
		this.repository = repository;
	}

	public Pessoa create(PessoaToCreateDto pessoaDto) throws CpfException {
		Pessoa pessoa = pessoaDto.transformaParaObjeto();

		if (pessoa == null) return null;

		Pessoa pessoaExistente = repository.findByCpf(pessoa.getCpf());

		if (pessoaExistente != null) throw new CpfException("CPF já existente.");

		return repository.save(pessoa);
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

	public Pessoa update(String id, PessoaToUpdateDto dto) {
		Pessoa pessoaExistente = this.findById(id);

		if (pessoaExistente == null) return null;

		Pessoa pessoa = dto.transformaParaObjeto(pessoaExistente.getCreateAt());

		if (pessoa == null) return null;

		return repository.save(pessoa);
	}

	public Boolean delete(String id) {
		Pessoa pessoaExistente = this.findById(id);

		if (pessoaExistente == null) return false;

		repository.delete(pessoaExistente);

		return true;
	}
}
