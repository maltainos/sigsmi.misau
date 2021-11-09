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
@Table(name = "levels")
public class UserLevelEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 30, unique = true)
	private String levelId;
	
	@Column(nullable = false, length = 30, unique = true)
	private String levelName;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "levels_roles", joinColumns = @JoinColumn(name = "levels_id"),
	inverseJoinColumns = @JoinColumn(name = "roles_id"))
	private List<RoleEntity> roles;
	
	@Column(nullable = false)
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
}








