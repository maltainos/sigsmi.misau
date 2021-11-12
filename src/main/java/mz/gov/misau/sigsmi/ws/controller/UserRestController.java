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
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
import mz.gov.misau.sigsmi.ws.ui.model.request.PasswordResetRequestModel;
import mz.gov.misau.sigsmi.ws.ui.model.request.UserRequestDetailsModel;
import mz.gov.misau.sigsmi.ws.ui.model.response.MensagemErro;
import mz.gov.misau.sigsmi.ws.ui.model.response.OperationStatusModel;
import mz.gov.misau.sigsmi.ws.ui.model.response.RequestOperationName;
import mz.gov.misau.sigsmi.ws.ui.model.response.RequestOperationStatus;
import mz.gov.misau.sigsmi.ws.ui.model.response.RoleRest;
import mz.gov.misau.sigsmi.ws.ui.model.response.UserLevelRest;
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
		UserDTO usersDTO =  userService.findUserByEmail(email);
		UserRest returnValue = MAPPER.map(usersDTO, UserRest.class);
		
		returnValue.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
				.methodOn(UserRestController.class).findUserByUserId(
						returnValue.getUserId())).withSelfRel());
		
		for(UserLevelRest levelRest : returnValue.getGroups()) {
			levelRest.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
					UserLevelRestController.class).findByLevelId(
							levelRest.getLevelId())).withSelfRel());
			for(RoleRest roleRest : levelRest.getRoles()) {
				roleRest.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
						RoleRestController.class).findByRoleId(roleRest.getRoleId())).withSelfRel());
			}
		}
		
		return returnValue;
	}
	
	@GetMapping(path = "/{userId}")
	public UserRest findUserByUserId(@PathVariable String userId){
		UserDTO usersDTO =  userService.findUserByUserId(userId);
		
		UserRest returnValue = MAPPER.map(usersDTO, UserRest.class);
		
		returnValue.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				UserRestController.class).findUserByUserId(returnValue.getUserId())).withSelfRel());
		
		for(UserLevelRest levelRest : returnValue.getGroups()) {
			levelRest.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
					UserLevelRestController.class).findByLevelId(
							levelRest.getLevelId())).withSelfRel());
			
			for(RoleRest roleRest : levelRest.getRoles()) {
				roleRest.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
						RoleRestController.class).findByRoleId(roleRest.getRoleId())).withSelfRel());
			}
		}
		
		return returnValue;
	}
	
	@PostMapping
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserRequestDetailsModel userDetails,
			HttpServletResponse response) {
		UserDTO userDTO = MAPPER.map(userDetails, UserDTO.class);
		userDTO = userService.createUser(userDTO);
		publisher.publishEvent(new CreateResourceEvent(this, response, userDTO.getUserId()));
		
		UserRest returnValue = MAPPER.map(userDTO, UserRest.class);
		returnValue.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				UserRestController.class).findUserByUserId(returnValue.getUserId())).withSelfRel());

		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}
	
	@PutMapping(path = "/{userId}")
	public ResponseEntity<UserRest> updateUser(@Valid @RequestBody UserRequestDetailsModel userDetails, 
			@PathVariable String userId){
		UserDTO userDTO = MAPPER.map(userDetails, UserDTO.class);
		userDTO = userService.updateUser(userDTO, userId);
		UserRest returnValue = MAPPER.map(userDTO, UserRest.class);
		
		returnValue.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				UserRestController.class).findUserByUserId(returnValue.getUserId())).withSelfRel());
		
//		for(UserLevelRest levelRest : returnValue.getGroups()) {
//			levelRest.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
//					UserLevelRestController.class).findByLevelId(
//							levelRest.getLevelId())).withSelfRel());
//			
//			for(RoleRest roleRest : levelRest.getRoles()) {
//				roleRest.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
//						RoleRestController.class).findByRoleId(roleRest.getRoleId())).withSelfRel());
//			}
//		}
		
		return ResponseEntity.ok(returnValue);
	}
	
	@DeleteMapping(path = "/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable String userId){
		userService.deleteUser(userId);
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.DELETE);
		returnValue.setOperationStatus(RequestOperationStatus.SUCCESS);
		
//		UserRest returnValue = new UserRest();
//		returnValue.add(WebMvcLinkBuilder.linkTo(
//				WebMvcLinkBuilder.methodOn(UserRestController.class)
//				.deleteUser(userId)).withSelfRel());
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping(path = "/email-verification")
	public ResponseEntity<OperationStatusModel> verifyEmailToken(@RequestParam(value = "token") String token){
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.EMAIL_VERIFICATION);
		returnValue.setOperationStatus(RequestOperationStatus.ERROR);
		
		if(userService.virifyEmailToken(token)) 
			returnValue.setOperationStatus(RequestOperationStatus.SUCCESS);
		return ResponseEntity.ok(returnValue);
	}
	
	@PostMapping(path = "/password-reset-request")
	public ResponseEntity<OperationStatusModel> requestReset(@RequestBody 
			PasswordResetRequestModel passwordResetModel) {
		OperationStatusModel returnValue = new OperationStatusModel();
		
		boolean operationResult = userService.requestPasswordReset(
				passwordResetModel.getEmail());
		
		returnValue.setOperationName(RequestOperationName.REQUEST_PASSWORD_RESET);
		
		if(operationResult) returnValue.setOperationStatus(RequestOperationStatus.SUCCESS);
		else returnValue.setOperationStatus(RequestOperationStatus.ERROR);
		
		return ResponseEntity.ok(returnValue);
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










