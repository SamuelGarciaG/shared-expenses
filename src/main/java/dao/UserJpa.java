package dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.User;

public interface UserJpa extends JpaRepository<User,Integer> {

	List<User> findByfidparty(int fidparty);

}
