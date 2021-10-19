package mz.gov.misau.sigsmi.ws.ui.model.request;

import java.time.LocalDate;
import java.util.List;

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
public class PacienteRequestDetailsModel {

	@NotEmpty
	@NotNull
	@Size(min = 3, max = 30)
	private String primeiroNome;
	
	@NotEmpty
	@NotNull
	@Size(min = 3, max = 30)
	private String sobreNome;
	
	private LocalDate dataNascimento;
	private byte anosIdade;
	private FiliacaoRequestDetailsModel filiacao;
	
	@NotNull
	private EstadoCivilRequestDetailsModel estadoCivil;
	private ProfissaoPacienteRequestDetailsModel profissao;
	private List<EnderecoRequestDetailsModel> enderecos;
	private List<ContactoRequestDetailsModel> contactos;
	private PessoaReferenciaPacienteRequestDetailsModel pessoaReferencia;
	@NotNull
	private GrupoSanguineoRequestDetailsModel grupoSanguineo;

}










