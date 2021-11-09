package mz.gov.misau.sigsmi.ws;

import java.util.Locale;

import org.hibernate.validator.spi.messageinterpolation.LocaleResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import mz.gov.misau.sigsmi.ws.security.AppProperties;

@SpringBootApplication
public class MisauSigsmiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MisauSigsmiApplication.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}
	
	@Bean(name = "AppProperties")
	public AppProperties appProperties() {
		return new AppProperties();
	}
	
//	@Bean
//	public LocaleResolver localeResolver() {
//		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
//		localeResolver.setDefaultLocale(Locale.US);
//		return (LocaleResolver) localeResolver;
//	}

}






















