package mz.gov.misau.sigsmi.ws.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import mz.gov.misau.sigsmi.ws.shared.dto.UserDTO;

public interface UserService extends UserDetailsService{

	public List<UserDTO> findUsers(int page, int limit);
	public void deleteUser(String userId);
	public UserDTO createUser(UserDTO userDTO);
	public UserDTO findUserByEmail(String email);
	public UserDTO findUserByUserId(String userId);
	public UserDTO updateUser(UserDTO userDTO, String userId);
}
