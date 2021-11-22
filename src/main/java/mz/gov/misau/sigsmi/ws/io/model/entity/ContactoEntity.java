package mz.gov.misau.sigsmi.ws.io.model.entity;

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

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "contactos")
public class ContactoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 30, unique = true)
	private String contactoId;

	@Column(nullable = false, length = 13, unique = true)
	private String numeroTelefone;

	@ManyToOne
	@JoinColumn(name = "paciente_id")
	private PacienteEntity paciente;
}









