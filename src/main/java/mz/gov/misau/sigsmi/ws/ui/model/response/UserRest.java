package mz.gov.misau.sigsmi.ws.ui.model.response;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserRest extends RepresentationModel<UserRest>{
	
	private String userId;
	private String firstName;
	private String lastName;
	private String login;
	private String email;
	private List<UserLevelRest> groups;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
}







