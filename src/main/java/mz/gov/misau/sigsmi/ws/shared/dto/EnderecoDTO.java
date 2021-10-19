package mz.gov.misau.sigsmi.ws.shared.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class EnderecoDTO {

	private Long id;

	private String enderecoId;
	private String residencia;
	private String districto;
	private String bairro;
	private String avenida;
	private String rua;
	private String nrCasa;
	private String telefoneCasa;
	private PacienteDTO paciente;

}
