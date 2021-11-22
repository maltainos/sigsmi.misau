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
import mz.gov.misau.sigsmi.ws.io.model.enumeration.DoseVacina;

@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "vacinas_gravidez")
public class VacinacaoGravidezEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true, length = 30)
	private String vacina_Id;
	
	@Enumerated(EnumType.STRING)
	private DoseVacina  doseVacina;
	
	@Column(nullable = false)
	private LocalDateTime dataVacina;
	
	private String localVacina;
	
	@ManyToOne
	@JoinColumn(name = "gravidez_id")
	private GravidezEntity gravidez;
}





