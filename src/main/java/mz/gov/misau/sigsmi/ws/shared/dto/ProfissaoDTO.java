package mz.gov.misau.sigsmi.ws.shared.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ProfissaoDTO {
	
	private String profissao;
	private String cargo;
	private String localTrabalho;
}
