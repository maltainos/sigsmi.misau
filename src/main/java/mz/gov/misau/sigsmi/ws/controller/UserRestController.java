package mz.gov.misau.sigsmi.ws.controller;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mz.gov.misau.sigsmi.ws.exception.resource.UserNameOrEmailExistException;
import mz.gov.misau.sigsmi.ws.exception.resource.UserNotFoundException;
import mz.gov.misau.sigsmi.ws.service.impl.UserServiceImpl;
import mz.gov.misau.sigsmi.ws.shared.dto.UserDTO;
import mz.gov.misau.sigsmi.ws.ui.event.CreateResourceEvent;
import mz.gov.misau.sigsmi.ws.ui.model.request.UserRequestDetailsModel;
import mz.gov.misau.sigsmi.ws.ui.model.response.MensagemErro;
import mz.gov.misau.sigsmi.ws.ui.model.response.RequestOperationDetailsModel;
import mz.gov.misau.sigsmi.ws.ui.model.response.RequestOperationName;
import mz.gov.misau.sigsmi.ws.ui.model.response.RequestOperationStatus;
import mz.gov.misau.sigsmi.ws.ui.model.response.UserRest;

@RestController
@RequestMapping(path = "/users")
public class UserRestController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	private final ModelMapper MAPPER = new ModelMapper();

	@GetMapping
	public List<UserRest> searchUsers(@RequestParam(value = "page",  defaultValue = "0")int page, 
			@RequestParam(value = "limit", defaultValue = "25")int limit){
		List<UserDTO> usersDTO =  userService.findUsers(page, limit);
		Type usersDTOType = new TypeToken<List<UserRest>>() {}.getType();
		return MAPPER.map(usersDTO, usersDTOType);
	}
	
	@GetMapping(path = "/email/{email}")
	public UserRest findUserByEmail(@PathVariable String email){
		UserDTO usersDTO =  userService.findUserByUserId(email);
		return MAPPER.map(usersDTO, UserRest.class);
	}
	
	@GetMapping(path = "/{userId}")
	public UserRest findUserByUserId(@PathVariable String userId){
		UserDTO usersDTO =  userService.findUserByUserId(userId);
		
		//org.springframework.hateoas.
		//Resource<UserRest> resource = new Resource<UserRest>(usersDTO);
		return MAPPER.map(usersDTO, UserRest.class);
	}
	
	@PostMapping
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserRequestDetailsModel userDetails,
			HttpServletResponse response) {
		UserDTO userDTO = MAPPER.map(userDetails, UserDTO.class);
		userDTO = userService.createUser(userDTO);
		publisher.publishEvent(new CreateResourceEvent(this, response, userDTO.getUserId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(MAPPER.map(userDTO, UserRest.class));
	}
	
	@PutMapping(path = "/{userId}")
	public ResponseEntity<UserRest> updateUser(@Valid @RequestBody UserRequestDetailsModel userDetails, 
			@PathVariable String userId){
		UserDTO userDTO = MAPPER.map(userDetails, UserDTO.class);
		userDTO = userService.updateUser(userDTO, userId);
		return ResponseEntity.ok(MAPPER.map(userDTO, UserRest.class));
	}
	
	@DeleteMapping(path = "/{userId}")
	public ResponseEntity<RequestOperationDetailsModel> deleteUser(@PathVariable String userId){
		userService.deleteUser(userId);
		RequestOperationDetailsModel operationName = new RequestOperationDetailsModel();
		operationName.setOperationName(RequestOperationName.DELETE);
		operationName.setOperationStatus(
				new RequestOperationStatus(HttpStatus.NO_CONTENT.value(), 
						HttpStatus.NO_CONTENT.toString()));
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(operationName);
	}
	
	@ExceptionHandler({UserNameOrEmailExistException.class})
	public ResponseEntity<Object> handleUserNameOrEmailException(UserNameOrEmailExistException ex){
		MensagemErro error = new MensagemErro(HttpStatus.IM_USED.value(), 
				messageSource.getMessage("username.invalido", null, LocaleContextHolder.getLocale()),
				LocalDateTime.now(),ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler({UserNotFoundException.class})
	public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex){
		MensagemErro error = new MensagemErro(HttpStatus.NOT_FOUND.value(), 
				messageSource.getMessage("user.user-not-found", null, LocaleContextHolder.getLocale()),
				LocalDateTime.now(), ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

}










