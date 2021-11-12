package mz.gov.misau.sigsmi.ws.ui.model.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PacienteRest extends RepresentationModel<PacienteRest>{
	
	private String pacienteId;
	private String primeiroNome;
	private String sobreNome;
	private LocalDate dataNascimento;
	private byte anosIdade;
	private FiliacaoRest filiacao;
	private EstadoCivilRest estadoCivil;
	private ProfissaoRest profissao;
	private List<EnderecoRest> enderecos;
	private List<ContactoRest> contactos;
	private PessoaReferenciaRest pessoaReferencia;
	private GrupoSanguineoRest grupoSanguineo;
	private LocalDateTime criadoEm;
	private LocalDateTime atualizadoEm;

}
