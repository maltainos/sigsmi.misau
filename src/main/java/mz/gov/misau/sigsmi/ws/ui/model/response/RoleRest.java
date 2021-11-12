package mz.gov.misau.sigsmi.ws.ui.model.response;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRest extends RepresentationModel<RoleRest>{
	
	private String roleId;
	private String roleName;
	private String roleDescription;
}
