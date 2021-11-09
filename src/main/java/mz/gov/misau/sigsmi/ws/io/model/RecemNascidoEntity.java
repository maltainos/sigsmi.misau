package mz.gov.misau.sigsmi.ws.io.model;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "recem_nascido")
public class RecemNascidoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true, length = 30)
	private String recemNascidoId;
	
	@Column(nullable = false, length = 500)
	private String estadoGeral;
	private String dispneia;
	
	@Column(nullable = false)
	private String coloracao;
	private String icteria;
	
	@Column(nullable = false)
	private String temperatura;
	
	@Column(nullable = false, columnDefinition = "Boolean default true")
	private boolean chupaBem;
	
	@Column(nullable = false, length = 255)
	private String estadoCotoUmbilical;
	
	@Column(nullable = false, columnDefinition = "Boolean default false")
	private boolean irritabilidade;
	
	@Enumerated(EnumType.STRING)
	private FortanelaAnterior fortenala;
	
	@Column(nullable = false, columnDefinition = "Boolean default false")
	private boolean temMalFormacao = false;
	private String malFormacaoNome;

	@ManyToOne
	@JoinColumn(name = "gravidez_id")
	private GravidezEntity gravidez;
}
















