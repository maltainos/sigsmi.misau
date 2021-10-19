package mz.gov.misau.sigsmi.ws.shared.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class FiliacaoDTO {
	
	private String nomePai;
	private String ocupacaoPai;
	private String nomeMae;
	private String ocupacaoMae;
}
