package mz.gov.misau.sigsmi.ws.exception.resource;

import lombok.Getter;

@Getter
public class RoleNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String message;
	
	public RoleNotFoundException(String message) {
		super();
		this.message = message;
	}
}
