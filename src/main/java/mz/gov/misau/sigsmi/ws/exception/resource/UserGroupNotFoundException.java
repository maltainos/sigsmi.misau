package mz.gov.misau.sigsmi.ws.exception.resource;

import lombok.Getter;

@Getter
public class UserGroupNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String message;
	
	public UserGroupNotFoundException(String message) {
		super();
		this.message = message;
	}
}
