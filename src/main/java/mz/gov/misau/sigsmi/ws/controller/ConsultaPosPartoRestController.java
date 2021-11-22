package mz.gov.misau.sigsmi.ws.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mz.gov.misau.sigsmi.ws.ui.model.response.ConsultaPosPartoRest;

@RestController
@RequestMapping(path = "consultas-pos-parto")
public class ConsultaPosPartoRestController {
	
	@GetMapping
	public List<?> procurar(){
		return new ArrayList<>();
	}
	
	@GetMapping(path = "/{consultaCodigo}")
	public ConsultaPosPartoRest buscar(@PathVariable String consultaCodigo) {
		
		return new ConsultaPosPartoRest();
	}

}























