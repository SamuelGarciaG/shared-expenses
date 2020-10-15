package dao;

import java.util.List;
import java.util.Optional;

import model.Party;

public interface PartyDao {

	public List<Party> getPartys();
	public Party addParty(Party Party);
	public Optional<Party> getPartyById(int party);
}
