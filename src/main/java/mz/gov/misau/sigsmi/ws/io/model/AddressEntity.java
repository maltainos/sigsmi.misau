package mz.gov.misau.sigsmi.ws.io.model;

import java.io.Serializable;

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
public class AddressEntity implements Serializable{

	@JsonIgnore
	@Transient
	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	private String addressId;
	private String city;
	private String street;
	private String av;
	private String cell;
}
























