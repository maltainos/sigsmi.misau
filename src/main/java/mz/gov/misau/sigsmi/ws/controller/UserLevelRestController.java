package mz.gov.misau.sigsmi.ws.controller;

import java.lang.reflect.Type;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mz.gov.misau.sigsmi.ws.service.impl.UserLevelServiceImpl;
import mz.gov.misau.sigsmi.ws.shared.dto.UserLevelDTO;
import mz.gov.misau.sigsmi.ws.ui.model.request.UserLevelRequestDetailsModel;
import mz.gov.misau.sigsmi.ws.ui.model.response.UserLevelRest;

@RestController
@RequestMapping(path = "groups")
public class UserLevelRestController {
	
	@Autowired
	private UserLevelServiceImpl userLevelService;
	
	private final static ModelMapper MAPPER = new ModelMapper();
	
	@GetMapping
	public List<UserLevelRest> search(){
		List<UserLevelDTO> usersLevelDTO = userLevelService.usersLevels();
		Type userLevelType = new TypeToken<List<UserLevelRest>>() {}.getType();
		List<UserLevelRest> returnValue = MAPPER.map(usersLevelDTO, userLevelType);
		return returnValue;
	}
	
	@PostMapping
	public UserLevelRest create(@Valid @RequestBody UserLevelRequestDetailsModel userLevelDetails) {
		UserLevelDTO userLevelDTO = MAPPER.map(userLevelDetails, UserLevelDTO.class);
		userLevelDTO = userLevelService.create(userLevelDTO);
		return MAPPER.map(userLevelDTO, UserLevelRest.class);
	}

}














