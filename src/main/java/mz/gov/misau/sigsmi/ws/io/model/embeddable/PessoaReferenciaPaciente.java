package mz.gov.misau.sigsmi.ws.io.model.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class PessoaReferenciaPaciente {

	@Column(nullable = false, length = 30)
	private String nomePessoaReferencia;

	@Column(nullable = false, length = 13)
	private String telefoneEmergencia;
}