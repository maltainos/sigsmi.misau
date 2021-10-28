package mz.gov.misau.sigsmi.ws.ui.model.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class RequestOperationStatus {

	private int statusCode;
	private String statusName;
}
