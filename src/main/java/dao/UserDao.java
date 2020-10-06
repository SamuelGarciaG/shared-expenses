package dao;

import java.util.List;

import model.User;

public interface UserDao {
	
	public List<User> getUsers();
	public User addUser(User user);
	public List<User> getUsersByParty(int party);

}
