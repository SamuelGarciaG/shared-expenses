package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	UserJpa jpa;
	
	public UserDaoImpl(@Autowired UserJpa jpa) {
		this.jpa=jpa;
	}

	@Override
	public List<User> getUsers() {
		return jpa.findAll();
	}

	@Override
	public User addUser(User user) {
		return jpa.save(user);
	}

}
