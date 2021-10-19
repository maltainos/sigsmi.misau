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
public class EnderecoRequestDetailsModel {
	
	@NotEmpty
	@NotNull
	@Size(min = 3, max = 30)
	private String residencia;
	
	@NotEmpty
	@NotNull
	@Size(min = 3, max = 30)
	private String districto;
	private String bairro;
	private String avenida;
	private String rua;
	private String nrCasa;
	private String telefoneCasa;
}
