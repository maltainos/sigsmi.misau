package mz.gov.misau.sigsmi.ws.shared.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ContactoDTO {
	
	private Long id;
	private String contactoId;
	private String numeroTelefone;
	private PacienteDTO paciente;
}
