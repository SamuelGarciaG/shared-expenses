package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.PartyDao;
import model.Party;

@Service
public class PartyServiceImpl implements PartyService {

	PartyDao dao;
	
	public PartyServiceImpl(@Autowired PartyDao dao) {
		this.dao=dao;
	}
	
	@Override
	public List<Party> getPartys() {
		return dao.getPartys();
	}

	@Override
	public Party addParty(Party Party) {
		return dao.addParty(Party);
	}

	@Override
	public Party getPartyById(int party) {
		return dao.getPartyById(party);
	}

}
