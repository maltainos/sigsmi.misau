package mz.gov.misau.sigsmi.ws.io.model;

import lombok.Getter;

@Getter
public enum AbortoEnumeration {
	
	PROVOCADO("Gravidez com aborto provcado"),
	SEM_ABORTO("Gravidez sem aborto"),
	ESPONTANEO("Gravidez com aporto espontaneo");
	
	private String aborto;
	
	private AbortoEnumeration(String aborto) {
		this.aborto = aborto;
	}

}







