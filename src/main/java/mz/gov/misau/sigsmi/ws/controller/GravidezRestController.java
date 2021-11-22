package mz.gov.misau.sigsmi.ws.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mz.gov.misau.sigsmi.ws.io.repository.filter.GravidezFilter;
import mz.gov.misau.sigsmi.ws.ui.model.response.GravidezRest;

@RestController
@RequestMapping(path = "gravidezes")
public class GravidezRestController {

	@GetMapping
	public List<?> search(GravidezFilter gravidezFilter){
		return new ArrayList<>();
	}
	
	@GetMapping(path = "/{gravidezId}")
	public GravidezRest find(@PathVariable String gravidezId){
		return new GravidezRest();
	}
	
}
























