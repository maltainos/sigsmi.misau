package mz.gov.misau.sigsmi.ws.io.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
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

@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "consultas_pre_natais")
public class ConsultaPreNatalEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String codigoPvt;
	
	@Column(nullable = false, unique = true, length = 30)
	private String consultaPreNatalId;
	
	private String observacoes;
	
	private String recomendacoes;

	private Float pesoMae;
	
	@Column(nullable = false)
	private LocalDateTime dataConsulta;
	
	private LocalDateTime dataProvavelDeParto;
	
	@ManyToOne
	@JoinColumn(name = "gravidez_id")
	private GravidezEntity gravidez;

}














