package mz.gov.misau.sigsmi.ws.io.model;

import lombok.Getter;

@Getter
public enum GravidezStatus {
	
	ABORTADO("Gravidez foi abortado"),
	SAUDAVEL("Gravidez normal em andamento"),
	PARTO("Gravidez ja fez parto");
	
	private String gravidezStatus;
	
	private GravidezStatus(String gravidezStatus) {
		this.gravidezStatus = gravidezStatus;
	}

}
