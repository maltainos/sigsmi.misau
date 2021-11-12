package mz.gov.misau.sigsmi.ws.ui.model.response;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class UserLevelRest extends RepresentationModel<UserLevelRest>{
	
	private String levelId;
	private String levelName;
	private List<RoleRest> roles;

}





