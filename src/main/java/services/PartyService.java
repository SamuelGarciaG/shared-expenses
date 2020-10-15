package services;

import java.util.List;

import dto.UserDto;
import model.Party;

public interface PartyService {

	public List<Party> getPartys();
	public Party addParty(Party Party);
	public Party getPartyById(int party) throws Exception;
	public List<UserDto> getPartyBalance(int party);
}
