package mz.gov.misau.sigsmi.ws.ui.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequestDetails {
	
	private String email;
	private String password;

}
