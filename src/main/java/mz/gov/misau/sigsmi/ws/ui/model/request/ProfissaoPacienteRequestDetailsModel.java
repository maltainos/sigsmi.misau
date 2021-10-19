package mz.gov.misau.sigsmi.ws.ui.model.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ProfissaoPacienteRequestDetailsModel {
	
	private String profissao;
	private String cargo;
	private String localTrabalho;
}
