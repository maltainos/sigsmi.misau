package mz.gov.misau.sigsmi.ws.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {
	
	@Autowired
	private Environment env;
	
	public  String getTokenSecret() {
		return env.getProperty("tokenSecret");
	}
	
	public String getAccessKey() {
		return env.getProperty("accessKey");
	}
	
	public String getSecretKey() {
		return env.getProperty("secretKey");
	}

}
