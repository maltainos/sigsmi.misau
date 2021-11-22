package mz.gov.misau.sigsmi.ws.io.model.enumeration;

import lombok.Getter;

@Getter
public enum DoseVacina {
	
	DOSE_1("Vacina anti-tetanica 1 dose (1 contacto)"),
	DOSE_2("Vacina anti-tetanica 2 dose"),
	DOSE_3("Vacina anti-tetanica 3 dose"),
	DOSE_4("Vacina anti-tetanica 4 dose"),
	DOSE_5("Vacina anti-tetanica 5 dose"),
	DOSE_REFORCO("Vacina anti-tetanica reforco");
	
	private String vacinaMensagem;
	
	private DoseVacina(String vacinaMensagem) {
		this.vacinaMensagem = vacinaMensagem;
	}
	

}
