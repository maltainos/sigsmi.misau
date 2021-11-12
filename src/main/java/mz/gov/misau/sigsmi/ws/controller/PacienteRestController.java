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
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class PacienteRestController{
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	public PacienteServiceImpl pacienteService;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_SELECT_PATIENTSS')")
	public List<PacienteRest> search(){
		List<PacienteDTO> pacientes = pacienteService.search();
		ModelMapper mapper = new ModelMapper();
		Type pacientesRest = new TypeToken<List<PacienteRest>>() {}.getType();
		return mapper.map(pacientes, pacientesRest);
	}
	

	@GetMapping(path = "/{pacienteId}")
	@PreAuthorize("hasAuthority('ROLE_UPDATE_PATIENT')")
	public ResponseEntity<PacienteRest> findById(@PathVariable String pacienteId){
//		ModelMapper mapper = new ModelMapper();
//		PacienteDTO pacienteDTO = mapper.map(pacienteRequest, PacienteDTO.class);
//		pacienteDTO = pacienteService.update(pacienteDTO, pacienteId);
		return ResponseEntity.ok(null);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CREATE_PATIENT')")
	public ResponseEntity<PacienteRest> create(@Valid @RequestBody PacienteRequestDetailsModel pacienteRequest, HttpServletResponse response) {
		ModelMapper mapper = new ModelMapper();
		PacienteDTO pacienteDTO = mapper.map(pacienteRequest, PacienteDTO.class);
		pacienteDTO = pacienteService.create(pacienteDTO);
		
		PacienteRest returnValue = mapper.map(pacienteDTO, PacienteRest.class);
		returnValue.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				PacienteRestController.class).findById(returnValue.getPacienteId())).withSelfRel());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}
	
	
	@GetMapping(path = "/{pacienteId}/enderecos")
	@PreAuthorize("hasAuthority('ROLE_SELECT_PATIENT')")
	public List<EnderecoRest> searchAddresses(@PathVariable String pacienteId) {
		List<EnderecoDTO> enderecos = pacienteService.searchAddresses(pacienteId);
		ModelMapper mapper = new ModelMapper();
		Type enderecoTypeList = new TypeToken<List<EnderecoRest>>() {}.getType();
		return mapper.map(enderecos, enderecoTypeList);
	}
	
	@PostMapping(path = "/{pacienteId}/enderecos")
	@PreAuthorize("hasAuthority('ROLE_CREATE_PATIENT')")
	public ResponseEntity<EnderecoRest> create(@Valid @RequestBody EnderecoRequestDetailsModel enderecoRequest, @PathVariable String pacienteId, HttpServletResponse response) {
		ModelMapper mapper = new ModelMapper();
		EnderecoDTO enderecoDTO = mapper.map(enderecoRequest, EnderecoDTO.class);
		enderecoDTO = pacienteService.create(enderecoDTO, pacienteId);
		return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(enderecoDTO, EnderecoRest.class));
	}
	
	@PutMapping(path = "/{pacienteId}")
	@PreAuthorize("hasAuthority('ROLE_UPDATE_PATIENT')")
	public ResponseEntity<PacienteRest> update(@Valid @RequestBody PacienteRequestDetailsModel pacienteRequest, @PathVariable String pacienteId){
		ModelMapper mapper = new ModelMapper();
		PacienteDTO pacienteDTO = mapper.map(pacienteRequest, PacienteDTO.class);
		pacienteDTO = pacienteService.update(pacienteDTO, pacienteId);
		return ResponseEntity.ok(mapper.map(pacienteDTO, PacienteRest.class));
	}
	
	@PutMapping(path = "/{pacienteId}/enderecos/{enderecoId}")
	@PreAuthorize("hasAuthority('ROLE_UPDATE_PATIENT')")
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
						LocaleContextHolder.getLocale()),LocalDateTime.now(),ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler({EnderecoNotFoundException.class})
	public ResponseEntity<Object> handleEnderecoNotFoundException(EnderecoNotFoundException ex){
		MensagemErro error = new MensagemErro(HttpStatus.NOT_FOUND.value(),
				messageSource.getMessage("recurso.recurso-nao-encontrado", null, 
						LocaleContextHolder.getLocale()), LocalDateTime.now(),ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
}












