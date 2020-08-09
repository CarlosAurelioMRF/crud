package br.com.carlosaurelio.crud.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.carlosaurelio.crud.dto.SourceDto;

@RestController
public class SourceController {
	@RequestMapping(value = "/source", method = RequestMethod.GET)
	public ResponseEntity<SourceDto>  Get() {
		SourceDto source = new SourceDto("https://github.com/CarlosAurelioMRF/crud");

		return new ResponseEntity<SourceDto>(source, HttpStatus.OK);
	}
}
