package mz.gov.misau.sigsmi.ws.ui.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

@Getter
public class CreateResourceEvent extends ApplicationEvent{

	private static final long serialVersionUID = 1L;
	private String resourceId;
	private HttpServletResponse response;
	
	public CreateResourceEvent(Object source, HttpServletResponse response, String resourceId) {
		super(source);
		this.resourceId = resourceId;
		this.response = response;
	}
}
