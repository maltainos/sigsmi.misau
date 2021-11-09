package mz.gov.misau.sigsmi.ws.service.impl;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.gov.misau.sigsmi.ws.io.model.UserLevelEntity;
import mz.gov.misau.sigsmi.ws.io.repository.UserLevelRepository;
import mz.gov.misau.sigsmi.ws.service.UserLevelService;
import mz.gov.misau.sigsmi.ws.shared.MyUtils;
import mz.gov.misau.sigsmi.ws.shared.dto.UserLevelDTO;

@Service
public class UserLevelServiceImpl implements UserLevelService{
	
	@Autowired
	private MyUtils myUtils;
	
	@Autowired
	private UserLevelRepository userLevelRepository;
	
	private final static ModelMapper MAPPER = new ModelMapper();

	@Override
	public List<UserLevelDTO> usersLevels() {
		List<UserLevelEntity> usersLevels = userLevelRepository.findAll();
		Type userLevelType = new TypeToken<List<UserLevelDTO>>(){} .getType();
		List<UserLevelDTO> returnValue = MAPPER.map(usersLevels, userLevelType);
		return returnValue;
	}

	@Override
	public UserLevelDTO create(UserLevelDTO userLevelDTO) {
		userLevelDTO.setLevelId(myUtils.generateLogin(30));
		userLevelDTO.setCreateOn(LocalDateTime.now());
		UserLevelEntity userLevelStore = MAPPER.map(userLevelDTO, UserLevelEntity.class);
		UserLevelEntity userLevelEntity = userLevelRepository.save(userLevelStore);
		return MAPPER.map(userLevelEntity, UserLevelDTO.class);
	}

	@Override
	public UserLevelDTO update(UserLevelDTO userLevelDTo, String levelId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String levelId) {
		// TODO Auto-generated method stub
		
	}

}
