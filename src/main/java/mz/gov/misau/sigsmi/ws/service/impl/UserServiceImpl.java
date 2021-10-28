package mz.gov.misau.sigsmi.ws.service.impl;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import mz.gov.misau.sigsmi.ws.exception.resource.UserNameOrEmailExistException;
import mz.gov.misau.sigsmi.ws.exception.resource.UserNotFoundException;
import mz.gov.misau.sigsmi.ws.io.model.UserEntity;
import mz.gov.misau.sigsmi.ws.io.repository.UserRepository;
import mz.gov.misau.sigsmi.ws.service.UserService;
import mz.gov.misau.sigsmi.ws.shared.MyUtils;
import mz.gov.misau.sigsmi.ws.shared.dto.UserDTO;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private MyUtils myUtils;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private ModelMapper MAPPER = new ModelMapper();
	
	@Override
	public UserDTO createUser(UserDTO userDTO) {
		Optional<UserEntity> userFound = userRepository.findByLogin(userDTO.getLogin());
		if(userFound.isPresent())
			throw new UserNameOrEmailExistException("UserNameOrEmailExistException");
		userFound = userRepository.findByEmail(userDTO.getEmail());
		if(userFound.isPresent())
			throw new UserNameOrEmailExistException("UserNameOrEmailExistException");
		
		userDTO.setUserId(myUtils.generateUrlResource(30));
		userDTO.setLogin(myUtils.generateLogin(8));
		userDTO.setCreatedOn(LocalDateTime.now());
		UserEntity userForSave = MAPPER.map(userDTO, UserEntity.class);
		userForSave.setEncryptedPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
		
		UserEntity userSaved = userRepository.save(userForSave);
		return MAPPER.map(userSaved, UserDTO.class);
	}

	public List<UserDTO> findUsers() {

		List<UserEntity> users = userRepository.findAll();
		Type usersTyped = new TypeToken<List<UserEntity>>() {}.getType();
		return MAPPER.map(users, usersTyped);
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO, String userId) {
		Optional<UserEntity> userFound = userRepository.findByUserId(userDTO.getUserId());
		if(!userFound.isPresent())
			throw new UserNotFoundException("UserNotFoundException");
		userDTO.setId(userFound.get().getId());
		userDTO.setUpdatedOn(LocalDateTime.now());
		UserEntity updateUser = MAPPER.map(userDTO, UserEntity.class);
		UserEntity updatedUser = userRepository.save(updateUser);
		
		UserDTO returnValue = MAPPER.map(updatedUser, UserDTO.class);
		return returnValue;
	}

	@Override
	public UserDTO findUserByEmail(String email) {
		Optional<UserEntity> findUser = userRepository.findByEmail(email);
		if(!findUser.isPresent())
			throw new UserNotFoundException("UserNotFoundException");
		return MAPPER.map(findUser.get(), UserDTO.class);
	}

	@Override
	public UserDTO findUserByLogin(String login) {
		Optional<UserEntity> findUser = userRepository.findByLogin(login);
		if(!findUser.isPresent())
			throw new UserNotFoundException("UserNotFoundException");
		return MAPPER.map(findUser.get(), UserDTO.class);
	}

	@Override
	public UserDTO findUserByUserId(String userId) {
		Optional<UserEntity> findUser = userRepository.findByUserId(userId);
		if(!findUser.isPresent())
			throw new UserNotFoundException("UserNotFoundException");
		return MAPPER.map(findUser.get(), UserDTO.class);
	}

	public void deleteUser(String userId) {
		UserDTO foundUser = findUserByUserId(userId);
		UserEntity deleteUser = MAPPER.map(foundUser, UserEntity.class);
		userRepository.delete(deleteUser);
	}

}






