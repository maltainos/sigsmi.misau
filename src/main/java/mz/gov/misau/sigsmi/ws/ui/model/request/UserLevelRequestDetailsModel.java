package mz.gov.misau.sigsmi.ws.ui.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLevelRequestDetailsModel {

	@NotBlank
	@Size(min = 3, max = 25)
	private String levelName;
}
