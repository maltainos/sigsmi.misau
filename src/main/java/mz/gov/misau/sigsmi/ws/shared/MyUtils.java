package mz.gov.misau.sigsmi.ws.shared;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import mz.gov.misau.sigsmi.ws.security.SecurityConstants;

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
	
	public static String generateEmailVerificationToken(String userId) {

		String token = Jwts.builder()
				.setSubject(userId)
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
				.compact();
		
		return token;
	}

	public static boolean hasTokenExpired(String token) {
		Claims claims = Jwts.parser().setSigningKey( SecurityConstants.getTokenSecret() )
				.parseClaimsJws(token).getBody();
		
		Date tokenExpirationTime = claims.getExpiration();
		Date todayDate = new Date();
		
		return tokenExpirationTime.before(todayDate);
	}

	public String generatePasswordResetToken(String userId) {
		
		String token = Jwts.builder()
				.setSubject(userId)
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.PASSWORD_RESET_EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
				.compact();
		
		return token;
	}

}















