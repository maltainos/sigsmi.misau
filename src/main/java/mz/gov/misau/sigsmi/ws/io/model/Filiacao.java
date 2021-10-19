package mz.gov.misau.sigsmi.ws.io.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class Filiacao {
	
	@Column(nullable = false, length = 35)
	private String nomePai;
	private String ocupacaoPai;

	@Column(nullable = false, length = 35)
	private String nomeMae;
	private String ocupacaoMae;
}
