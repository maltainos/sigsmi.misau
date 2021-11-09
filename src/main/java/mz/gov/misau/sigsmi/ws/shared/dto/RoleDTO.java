package mz.gov.misau.sigsmi.ws.shared.dto;

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
public class RoleDTO {
	
	private Long id;
	private String roleId;
	private String roleName;
	private String roleDescription;
}
