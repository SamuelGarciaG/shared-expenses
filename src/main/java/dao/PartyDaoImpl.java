package dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Party;

@Repository
public class PartyDaoImpl implements PartyDao{
	
	PartyJpa jpa;
	
	public PartyDaoImpl(@Autowired PartyJpa jpa) {
		this.jpa=jpa;
	}

	@Override
	public List<Party> getPartys() {
		return jpa.findAll();
	}

	@Override
	public Party addParty(Party Party) {
		return jpa.save(Party);
	}

	@Override
	public Optional<Party> getPartyById(int party) {
		return jpa.findById(party);
	}

}
