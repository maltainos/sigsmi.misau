package mz.gov.misau.sigsmi.ws.io.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "enderecos")
public class EnderecoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 30, unique = true)
	private String enderecoId;

	@Column(nullable = false, length = 30)
	private String residencia;

	@Column(nullable = false, length = 30)
	private String districto;
	private String bairro;
	private String avenida;
	private String rua;
	private String nrCasa;
	private String telefoneCasa;

	@ManyToOne
	@JoinColumn(name = "paciente_id")
	private PacienteEntity paciente;
	
}







