package mz.gov.misau.sigsmi.ws.io.model.embeddable;

import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class ProfissaoPaciente {

	private String profissao;
	private String cargo;
	private String localTrabalho;
}
