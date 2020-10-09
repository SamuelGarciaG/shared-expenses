package services;

import java.util.List;

import dto.UserDto;

public interface UserService {
	
	public List<UserDto> getUsers();
	public UserDto addUser(UserDto user);
	public List<UserDto> getUsersByParty(int party);

}
