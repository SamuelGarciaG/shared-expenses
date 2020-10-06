package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Party;

public interface PartyJpa extends JpaRepository<Party,Integer> {

}
