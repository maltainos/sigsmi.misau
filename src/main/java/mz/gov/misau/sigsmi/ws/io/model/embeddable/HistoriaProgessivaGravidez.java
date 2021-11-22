package mz.gov.misau.sigsmi.ws.io.model.embeddable;

import javax.persistence.Embeddable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Embeddable
public class HistoriaProgessivaGravidez {
	
	private String gesta;
	private String para;
	private String gEtopica;
	private String cesarianas;
	private int nadosVivos;
	private int nadosMortos;
	private int vivosAtuais;
	private boolean primigestaAlturaInferior15metros;
	//private SimOuNao primigestaComIdadeInferior16anos;
	//private SimOuNao partosAnterioresComVentose;
}





























