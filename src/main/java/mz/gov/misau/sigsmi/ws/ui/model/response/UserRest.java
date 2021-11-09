package mz.gov.misau.sigsmi.ws.ui.model.response;

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
public class UserRest {
	
	private String userId;
	private String firstName;
	private String lastName;
	private String login;
	private String email;
	private List<UserLevelRest> groups;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
}







