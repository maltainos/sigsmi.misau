package mz.gov.misau.sigsmi.ws.ui.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import mz.gov.misau.sigsmi.ws.ui.event.CreateResourceEvent;

@Component
public class CreateResourceListener implements ApplicationListener<CreateResourceEvent>{

	@Override
	public void onApplicationEvent(CreateResourceEvent event) {
		String resourceId = event.getResourceId();
		HttpServletResponse response = event.getResponse();
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequestUri()
				.path("/{resourceId}")
				.buildAndExpand(resourceId)
				.toUri();
		response.addHeader("Location", uri.toASCIIString());
	}

}















