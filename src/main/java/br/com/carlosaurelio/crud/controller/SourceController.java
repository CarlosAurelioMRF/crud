package br.com.carlosaurelio.crud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SourceController {
	@RequestMapping(value = "/source", method = RequestMethod.GET)
	public String Get() {
		return "https://github.com/CarlosAurelioMRF/crud";
	}
}
