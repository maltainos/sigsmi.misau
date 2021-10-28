package mz.gov.misau.sigsmi.ws.shared;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class MyUtils {
	
	private final String ALPHABET = "0123456789ABCDEFGHJIKLMNOPQRSTUVXWYZabcdefghijklmnopqrstuvxwyz";
	private final Random RANDOM = new SecureRandom();
	
	public String generateUrlResource(int length) {
		return generatedResourceId(length);
	}
	
	public String generateLogin(int length) {
		return generatedUserName(length);
	}

	private String generatedResourceId(int length) {
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < length; i++)
			builder.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		return builder.toString();
	}
	
	private String generatedUserName(int length) {
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < length; i++)
			builder.append(ALPHABET.charAt(RANDOM.nextInt(10)));
		return builder.toString();
	}

}















