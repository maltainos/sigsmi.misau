package mz.gov.misau.sigsmi.ws.io.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "users")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 30, unique = true)
	private String userId;
	
	@Column(nullable = false, length = 75)
	private String firstName;
	
	@Column(nullable = false, length = 25)
	private String lastName;
	
	@Column(name = "user_name", nullable = false, length = 8, unique = true)
	private String login;
	
	@Column(nullable = false, length = 20, unique = true)
	private String email;
	
	@Column(nullable =false, length = 120)
	private String encryptedPassword;
	
	private String emailVerificationToken;
	
	@Column(columnDefinition = "Boolean default false")
	private boolean emailVerificationStatus;
	
	/*
	@ManyToMany(cascade = CascadeType.ALL)
	private List<UserGroupEntity> groups;
	*/
	@Column(nullable = false)
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
	

}














