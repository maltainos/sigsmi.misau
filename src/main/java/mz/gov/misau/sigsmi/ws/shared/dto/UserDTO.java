package mz.gov.misau.sigsmi.ws.shared.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserDTO {
	
	private Long id;
	private String userId;
	private String firstName;
	private String lastName;
	private String login;
	private String email;
	private String password;
	private String encryptedPassword;
	private String emailVerificationToken;
	private boolean emailVerificationStatus;
	private List<UserLevelDTO> groups;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
}







