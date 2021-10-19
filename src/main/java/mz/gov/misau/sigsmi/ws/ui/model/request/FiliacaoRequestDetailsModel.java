package mz.gov.misau.sigsmi.ws.ui.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class FiliacaoRequestDetailsModel {
	
	@NotNull
	@NotEmpty
	private String nomePai;
	private String ocupacaoPai;
	
	@NotNull
	@NotEmpty
	private String nomeMae;
	private String ocupacaoMae;
}
