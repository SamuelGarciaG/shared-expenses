package dao;

import java.util.List;

import model.Party;

public interface PartyDao {

	public List<Party> getPartys();
	public Party addParty(Party Party);
	public Party getPartyById(int party);
}
