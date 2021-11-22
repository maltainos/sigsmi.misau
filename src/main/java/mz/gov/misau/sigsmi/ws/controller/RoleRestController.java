package mz.gov.misau.sigsmi.ws.controller;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mz.gov.misau.sigsmi.ws.exception.resource.RoleNotFoundException;
import mz.gov.misau.sigsmi.ws.io.model.entity.RoleEntity;
import mz.gov.misau.sigsmi.ws.io.repository.RoleRepository;
import mz.gov.misau.sigsmi.ws.ui.model.response.RoleRest;

@RestController
@RequestMapping(path = "/roles")
public class RoleRestController {
	
	@Autowired
	private RoleRepository roleRepository;
	
	private final ModelMapper MAPPER = new ModelMapper();
	
	@GetMapping
	public List<RoleRest> search(){
		List<RoleEntity> roles = roleRepository.findAll();
		
		Type roleTypeList = new TypeToken<List<RoleRest>>() {}.getType();
		List<RoleRest> returnValue = MAPPER.map(roles, roleTypeList);
		return returnValue;
	}
	
	@GetMapping(path = "/{roleId}")
	public ResponseEntity<RoleRest> findByRoleId(@PathVariable String roleId) {
		Optional<RoleEntity> role = roleRepository.findByRoleId(roleId);
		
		if(!role.isPresent())
			throw new RoleNotFoundException(RoleNotFoundException.class.toString());
		
		RoleRest returnValue = MAPPER.map(role.get(), RoleRest.class);
		
		returnValue.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				RoleRestController.class).findByRoleId(roleId)).withSelfRel());
		return ResponseEntity.ok(returnValue);
	}

}


























