package mz.gov.misau.sigsmi.ws.service;

import java.util.List;

import mz.gov.misau.sigsmi.ws.shared.dto.UserDTO;

public interface UserService {

	public List<UserDTO> findUsers();
	public void deleteUser(String userId);
	public UserDTO createUser(UserDTO userDTO);
	public UserDTO findUserByEmail(String email);
	public UserDTO findUserByLogin(String login);
	public UserDTO findUserByUserId(String userId);
	public UserDTO updateUser(UserDTO userDTO, String userId);
}
