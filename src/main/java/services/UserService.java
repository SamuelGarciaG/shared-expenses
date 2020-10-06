package services;

import java.util.List;

import model.User;

public interface UserService {
	
	public List<User> getUsers();
	public User addUser(User user);
	public List<User> getUsersByParty(int party);

}
