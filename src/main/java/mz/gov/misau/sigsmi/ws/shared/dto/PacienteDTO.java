package mz.gov.misau.sigsmi.ws.shared.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PacienteDTO {
	
	private Long id;
	private String pacienteId;
	private String primeiroNome;
	private String sobreNome;
	private LocalDate dataNascimento;
	private byte anosIdade;
	private FiliacaoDTO filiacao;
	private EstadoCivilDTO estadoCivil;
	private ProfissaoDTO profissao;
	private List<EnderecoDTO> enderecos;
	private List<ContactoDTO> contactos;
	private PessoaReferenciaDTO pessoaReferencia;
	private GrupoSanguineoDTO grupoSanguineo;
	private LocalDateTime criadoEm;
	private LocalDateTime atualizadoEm;
	
}








