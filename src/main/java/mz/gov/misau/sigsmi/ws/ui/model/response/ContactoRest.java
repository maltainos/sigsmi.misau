package mz.gov.misau.sigsmi.ws.ui.model.response;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactoRest extends RepresentationModel<ContactoRest>{
	
	private Long id;
	private String contactoId;
	private String numeroTelefone;
}
