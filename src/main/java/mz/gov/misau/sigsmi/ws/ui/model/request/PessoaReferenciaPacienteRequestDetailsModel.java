package mz.gov.misau.sigsmi.ws.ui.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PessoaReferenciaPacienteRequestDetailsModel {
	
	@NotEmpty
	@NotNull
	@Size(min = 3, max = 30)
	private String nomePessoaReferencia;
	
	@NotEmpty
	@NotNull
	@Size(min = 9, max = 13)
	private String telefoneEmergencia;
}
