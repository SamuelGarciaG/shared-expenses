package controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class UserDto {
	
	private int iduser;
	private String name;
	private int balance;
	private int fidparty;

}
