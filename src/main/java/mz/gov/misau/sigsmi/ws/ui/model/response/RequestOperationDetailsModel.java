package mz.gov.misau.sigsmi.ws.ui.model.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class RequestOperationDetailsModel {
	
	private RequestOperationName operationName;
	private RequestOperationStatus operationStatus;
}
