package br.com.carlosaurelio.crud.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.carlosaurelio.crud.dto.PessoaToCreateDto;
import br.com.carlosaurelio.crud.dto.PessoaToUpdateDto;
import br.com.carlosaurelio.crud.entity.Pessoa;
import br.com.carlosaurelio.crud.exception.CpfException;
import br.com.carlosaurelio.crud.service.PessoaService;

@RestController
@RequestMapping("/api")
public class PessoaController {

	private final PessoaService pessoaService;

	@Autowired
	public PessoaController(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}

	@RequestMapping(value = "/pessoas", method = RequestMethod.GET)
	public ResponseEntity<List<Pessoa>> Get() {
		List<Pessoa> pessoas = pessoaService.getAll();

		return new ResponseEntity<List<Pessoa>>(pessoas, HttpStatus.OK);
	}

	@RequestMapping(value = "/pessoas/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pessoa> GetById(@PathVariable(value = "id") String id) {
		Pessoa pessoa = pessoaService.findById(id);

		if (pessoa != null)
			return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

    @RequestMapping(value = "/pessoas", method =  RequestMethod.POST)
    public ResponseEntity<Pessoa> Post(@Valid @RequestBody PessoaToCreateDto dto) throws CpfException
    {
        Pessoa pessoa = pessoaService.create(dto);

        return new ResponseEntity<Pessoa>(pessoa, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/pessoas/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Pessoa> Put(@PathVariable(value = "id") String id, @Valid @RequestBody PessoaToUpdateDto dto)
    {
        Pessoa pessoa = pessoaService.update(id, dto);

        if(pessoa != null)
            return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/pessoas/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") String id)
    {
        Boolean excluido = pessoaService.delete(id);

        if (excluido) return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
