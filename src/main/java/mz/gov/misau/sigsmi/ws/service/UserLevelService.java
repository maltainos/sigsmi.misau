package mz.gov.misau.sigsmi.ws.service;

import java.util.List;

import mz.gov.misau.sigsmi.ws.shared.dto.UserLevelDTO;

public interface UserLevelService {
	
	public List<UserLevelDTO> usersLevels();
	public UserLevelDTO create(UserLevelDTO userLevelDTO);
	public UserLevelDTO update(UserLevelDTO userLevelDTo, String levelId);
	public void delete(String levelId);

}
