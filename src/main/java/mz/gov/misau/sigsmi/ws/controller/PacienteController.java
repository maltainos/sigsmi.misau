package mz.gov.misau.sigsmi.ws.controller;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mz.gov.misau.sigsmi.ws.exception.resource.EnderecoNotFoundException;
import mz.gov.misau.sigsmi.ws.exception.resource.PacienteNotFoundException;
import mz.gov.misau.sigsmi.ws.service.impl.PacienteServiceImpl;
import mz.gov.misau.sigsmi.ws.shared.dto.EnderecoDTO;
import mz.gov.misau.sigsmi.ws.shared.dto.PacienteDTO;
import mz.gov.misau.sigsmi.ws.ui.model.request.EnderecoRequestDetailsModel;
import mz.gov.misau.sigsmi.ws.ui.model.request.PacienteRequestDetailsModel;
import mz.gov.misau.sigsmi.ws.ui.model.response.EnderecoRest;
import mz.gov.misau.sigsmi.ws.ui.model.response.MensagemErro;
import mz.gov.misau.sigsmi.ws.ui.model.response.PacienteRest;

@RestController
@RequestMapping(path = "/pacientes")
public class PacienteController{
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	public PacienteServiceImpl pacienteService;
	
	@GetMapping
	public List<PacienteRest> search(){
		List<PacienteDTO> pacientes = pacienteService.search();
		ModelMapper mapper = new ModelMapper();
		Type pacientesRest = new TypeToken<List<PacienteRest>>() {}.getType();
		return mapper.map(pacientes, pacientesRest);
	}
	
	@PostMapping
	public ResponseEntity<PacienteRest> create(@Valid @RequestBody PacienteRequestDetailsModel pacienteRequest, HttpServletResponse response) {
		ModelMapper mapper = new ModelMapper();
		PacienteDTO pacienteDTO = mapper.map(pacienteRequest, PacienteDTO.class);
		pacienteDTO = pacienteService.create(pacienteDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(pacienteDTO, PacienteRest.class));
	}
	
	@GetMapping(path = "/{pacienteId}/enderecos")
	public List<EnderecoRest> searchAddresses(@PathVariable String pacienteId) {
		List<EnderecoDTO> enderecos = pacienteService.searchAddresses(pacienteId);
		ModelMapper mapper = new ModelMapper();
		Type enderecoTypeList = new TypeToken<List<EnderecoRest>>() {}.getType();
		return mapper.map(enderecos, enderecoTypeList);
	}
	
	@PostMapping(path = "/{pacienteId}/enderecos")
	public ResponseEntity<EnderecoRest> create(@Valid @RequestBody EnderecoRequestDetailsModel enderecoRequest, @PathVariable String pacienteId, HttpServletResponse response) {
		ModelMapper mapper = new ModelMapper();
		EnderecoDTO enderecoDTO = mapper.map(enderecoRequest, EnderecoDTO.class);
		enderecoDTO = pacienteService.create(enderecoDTO, pacienteId);
		return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(enderecoDTO, EnderecoRest.class));
	}
	
	@PutMapping(path = "/{pacienteId}")
	public ResponseEntity<PacienteRest> update(@Valid @RequestBody PacienteRequestDetailsModel pacienteRequest, @PathVariable String pacienteId){
		ModelMapper mapper = new ModelMapper();
		PacienteDTO pacienteDTO = mapper.map(pacienteRequest, PacienteDTO.class);
		pacienteDTO = pacienteService.update(pacienteDTO, pacienteId);
		return ResponseEntity.ok(mapper.map(pacienteDTO, PacienteRest.class));
	}
	
	@PutMapping(path = "/{pacienteId}/enderecos/{enderecoId}")
	public ResponseEntity<EnderecoRest> update(@Valid @RequestBody EnderecoRequestDetailsModel enderecoRequest,
			@PathVariable String pacienteId, @PathVariable String enderecoId){
		ModelMapper mapper = new ModelMapper();
		EnderecoDTO enderecoDTO = mapper.map(enderecoRequest, EnderecoDTO.class);
		enderecoDTO = pacienteService.update(enderecoDTO, pacienteId, enderecoId);
		return ResponseEntity.ok(mapper.map(enderecoDTO, EnderecoRest.class));
	}
	
	@ExceptionHandler({PacienteNotFoundException.class})
	public ResponseEntity<Object> handlePacienteNotFoundException(PacienteNotFoundException ex){
		MensagemErro error = new MensagemErro(HttpStatus.NOT_FOUND.value(), 
				messageSource.getMessage("recurso.recurso-nao-encontrado",null, 
						LocaleContextHolder.getLocale()),LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler({EnderecoNotFoundException.class})
	public ResponseEntity<Object> handleEnderecoNotFoundException(EnderecoNotFoundException ex){
		MensagemErro error = new MensagemErro(HttpStatus.NOT_FOUND.value(),
				messageSource.getMessage("recurso.recurso-nao-encontrado", null, 
						LocaleContextHolder.getLocale()), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
}












