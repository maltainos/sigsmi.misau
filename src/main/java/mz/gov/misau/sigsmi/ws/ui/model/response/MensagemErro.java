package mz.gov.misau.sigsmi.ws.ui.model.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MensagemErro {
	
	private int status;
	private String mensagem;
	private LocalDateTime timestamps;
}
