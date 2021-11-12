package mz.gov.misau.sigsmi.ws.ui.model.response;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoRest extends RepresentationModel<EnderecoRest>{

	private String enderecoId;
	private String residencia;
	private String districto;
	private String bairro;
	private String avenida;
	private String rua;
	private String nrCasa;
	private String telefoneCasa;
}
