package mz.gov.misau.sigsmi.ws.shared.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PessoaReferenciaDTO {
	
	private String nomePessoaReferencia;
	private String telefoneEmergencia;
}
