package mz.gov.misau.sigsmi.ws.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mz.gov.misau.sigsmi.ws.io.repository.filter.RecemNascidoFilter;
import mz.gov.misau.sigsmi.ws.ui.model.request.RecemNascidoRequestDetailsModel;
import mz.gov.misau.sigsmi.ws.ui.model.response.RecemNascidoRest;

@RestController
@RequestMapping(path = "/recem-nascidos")
public class RecemNascidoRestController {

	@GetMapping
	public List<?> getRecemNascidos(RecemNascidoFilter recemNascidoFilter){
		return new ArrayList<>();
	}
	
	@GetMapping(path = "/{recemNascidoId}")
	public RecemNascidoRest getRecemNascidos(@PathVariable String recemNascidoId){
		return new RecemNascidoRest();
	}
	
	@PutMapping(path = "/{recemNascidoId}")
	public RecemNascidoRest update(@PathVariable String recemNascidoId, @RequestBody RecemNascidoRequestDetailsModel recemNascido){
		return new RecemNascidoRest();
	}
	
}
























