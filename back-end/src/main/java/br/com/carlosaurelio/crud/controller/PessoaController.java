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

import br.com.carlosaurelio.crud.entity.Pessoa;
import br.com.carlosaurelio.crud.exception.CpfException;
import br.com.carlosaurelio.crud.service.PessoaService;

@RestController
@RequestMapping("/api")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@RequestMapping(value = "/pessoas", method = RequestMethod.GET)
	public List<Pessoa> Get() {
		return pessoaService.getAll();
	}

	@RequestMapping(value = "/pessoas/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pessoa> GetById(@PathVariable(value = "id") String id) {
		Pessoa pessoa = pessoaService.findById(id);

		if (pessoa != null)
			return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

    @RequestMapping(value = "/pessoas", method =  RequestMethod.POST)
    public Pessoa Post(@Valid @RequestBody Pessoa pessoa) throws CpfException
    {
        return pessoaService.create(pessoa);
    }

    @RequestMapping(value = "/pessoas/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Pessoa> Put(@Valid @PathVariable(value = "id") String id, @RequestBody Pessoa newPessoa)
    {
        Pessoa pessoa = pessoaService.update(id, newPessoa);

        if(pessoa != null)
            return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/pessoas/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") String id)
    {
        pessoaService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
