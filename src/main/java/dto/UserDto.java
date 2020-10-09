package dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class UserDto {
	
	private int iduser;
	private String name;
	private float balance;
	private int fidparty;
	
}
