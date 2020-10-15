package dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class UserDto {
	
	private int iduser;
	private String name;
	private double balance;
	private int fidparty;
	
}
