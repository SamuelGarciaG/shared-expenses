package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import model.User;

public interface UserJpa extends JpaRepository<User,Integer> {

}
