package mz.gov.misau.sigsmi.ws.io.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "addresses")
public class AddressEntity implements Serializable{

	@JsonIgnore
	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true, length = 30, name = "address_id")
	private String addressId;
	
	@Column(nullable = false, unique = true, length = 30)
	private String city;
	
	@Column(nullable = false, unique = true, length = 75)
	private String street;
	
	@Column(name = "avenida", length = 75)
	private String av;
	private String cell;
	
	@ManyToOne
	@JoinColumn(name = "users_id")
	private UserEntity userDetails;
}
























