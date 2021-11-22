package mz.gov.misau.sigsmi.ws.notification;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

import mz.gov.misau.sigsmi.ws.security.SecurityConstants;
import mz.gov.misau.sigsmi.ws.shared.dto.UserDTO;

public class AmazonSES {
	

	private String accessKey = SecurityConstants.getAccessKey();
	
	private String secretKey = SecurityConstants.getSecretKey();
	
	final String FROM = "zaonanelson@gmail.com";
	
	final String SUBJECT = "One last step to complete your registration with SIGCSMI-MISAU";
	
	final String HTMLBODY = "<h1>Plaese verify your email addresses</h1> "
			+ "Thank you for registering with our SIGSSMI-MISAU"
			+ "Here, registration confirmation click the link "
			+ "<a href='http://localhost:8080/verification-email-service/email-verification.html?token=$tokenValue'>"
			+ "Final step to complete your registration</a><br/><br>"
			+ "Thank you! And we are waiting for you aside";
	
	final String TEXTBODY = "Plaese verify your email addresses</h1> \"\r\n"
			+ "			+ \"Thank you for registering with our SIGSSMI-MISAU\"\r\n"
			+ "			+ \"Here, registration confirmation click the link \"\r\n"
			+ "			+ \"http://localhost:8080/verification-email-service/email-verification.html?token=$tokenValue'>\"\r\n"
			+ "			+ \"</br>Final step to complete your registration<br/><br>\"\r\n"
			+ "			+ \"Thank you! And we are waiting for you aside";
	
	
	public void verifyEmail(UserDTO user) {
		System.out.println("Access Key :"+accessKey);
		System.out.println("Secret Key :"+secretKey);
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(Regions.AF_SOUTH_1).build();
		
		String htmlStringWithToken = HTMLBODY.replace("$tokenValue", user.getEmailVerificationToken());
		String textStringWithToken = HTMLBODY.replace("$tokenValue", user.getEmailVerificationToken());
		
		SendEmailRequest request = new SendEmailRequest().withDestination(
				new Destination().withToAddresses(
						user.getEmail())).withMessage(new Message().withBody(
								new Body().withHtml(new Content().withCharset("UTF-8")
										.withData(htmlStringWithToken))
								.withText(new Content().withCharset("UTF-8").withData(textStringWithToken)))
								.withSubject(new Content().withCharset("UTF-8").withData(SUBJECT)));
		client.sendEmail(request);
		
		System.out.println("Send Email....");
	}

}










