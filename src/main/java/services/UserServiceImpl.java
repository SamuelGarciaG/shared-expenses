package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dao.UserDao;
import model.User;

public class UserServiceImpl implements UserService {
	
	UserDao dao;
	
	public UserServiceImpl(@Autowired UserDao dao) {
		this.dao=dao;
	}

	@Override
	public List<User> getUsers() {
		return dao.getUsers();
	}

	@Override
	public User addUser(User user) {
		return dao.addUser(user);
	}

}
