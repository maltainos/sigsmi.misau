package mz.gov.misau.sigsmi.ws.io.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
	
	@Column(nullable = false, length = 30, unique = true, name ="user_id")
	private String userId;
	
	@Column(nullable = false, length = 75, name = "first_name")
	private String firstName;
	
	@Column(nullable = false, length = 25, name = "last_name")
	private String lastName;
	
	@Column(nullable = false, length = 20, unique = true, name="email")
	private String email;
	
	@Column(nullable =false, length = 120, name = "encrypted_password")
	private String encryptedPassword;
	
	@Column(name = "email_verification_token")
	private String emailVerificationToken;
	
	@Column(name = "email_verification_status", columnDefinition = "Boolean default false")
	private boolean emailVerificationStatus;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "users_levels", joinColumns = @JoinColumn(name = "users_id"),
	inverseJoinColumns = @JoinColumn(name = "levels_id"))
	private List<UserLevelEntity> groups;
	
	@OneToMany(mappedBy = "userDetails", cascade = CascadeType.ALL)
	private List<AddressEntity> addresses;
	
	@Column(nullable = false, name ="created_on")
	private LocalDateTime createdOn;
	
	@Column(name ="updated_on")
	private LocalDateTime updatedOn;
	
	public boolean getEmailVerificationStatus() {
		return emailVerificationStatus;
	}

}














