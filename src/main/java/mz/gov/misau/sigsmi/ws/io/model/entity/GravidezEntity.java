
package mz.gov.misau.sigsmi.ws.io.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mz.gov.misau.sigsmi.ws.io.model.enumeration.AbortoEnumeration;
import mz.gov.misau.sigsmi.ws.io.model.enumeration.GravidezStatus;

@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "gravidezes")
public class GravidezEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true, length = 30)
	private String gravidezId;

	@Column(nullable = false)
	private LocalDateTime dataEngravida;
	private LocalDateTime dataParto;
	
	@Enumerated(EnumType.STRING)
	private GravidezStatus gravidezStatus;
	
	@Enumerated(EnumType.STRING)
	private AbortoEnumeration aborto = AbortoEnumeration.SEM_ABORTO;
	
	private String localParto;
	
	@ManyToOne
	@JoinColumn(name = "paciente_id")
	private PacienteEntity paciente;
}






