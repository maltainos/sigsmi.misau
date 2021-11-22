package mz.gov.misau.sigsmi.ws.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mz.gov.misau.sigsmi.ws.ui.model.response.ConsultaPreNatalRest;

@RestController
@RequestMapping(path = "consultas-pre-natais")
public class ConsultaPreNatalRestController {
	
	@GetMapping
	public List<?> procurar(){
		return new ArrayList<>();
	}
	
	@GetMapping(path = "/{consultaCodigo}")
	public ConsultaPreNatalRest buscar(@PathVariable String consultaCodigo) {
		
		return new ConsultaPreNatalRest();
	}

}


























