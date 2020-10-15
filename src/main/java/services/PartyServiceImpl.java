package services;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ExpenseDao;
import dao.PartyDao;
import dao.UserDao;
import dto.UserDto;
import model.Expense;
import model.Party;
import model.User;

@Service
public class PartyServiceImpl implements PartyService {

	ExpenseDao expenseDao;
	PartyDao partyDao;
	UserDao userDao;
	ModelMapper modelMapper;


	public PartyServiceImpl(@Autowired PartyDao partyDao, @Autowired ExpenseDao expenseDao, @Autowired UserDao userDao, @Autowired ModelMapper modelMapper) {
		this.partyDao=partyDao;
		this.expenseDao=expenseDao;
		this.userDao=userDao;
		this.modelMapper=modelMapper;
	}
	
	@Override
	public List<Party> getPartys() {
		return partyDao.getPartys();
	}

	@Override
	public Party addParty(Party Party) {
		return partyDao.addParty(Party);
	}

	@Override
	public Party getPartyById(int party) throws Exception {
		Optional<Party> partyOptional = partyDao.getPartyById(party);
		if(partyOptional.isPresent()){
		    Party partyObject = partyOptional.get();
		    return partyObject;
		}else{
		    throw new Exception();
		}
	}
	
	@Override
	public List<UserDto> getPartyBalance(int party) {
        List<User> users = userDao.getUsersByParty(party);
        List<UserDto> usersDto = users.stream().map(this::convertToDto).collect(Collectors.toList());
        usersDto = calculatePartyBalance(usersDto, party);
		return usersDto;
	}
	
	private UserDto convertToDto(User user) {
	    UserDto userDto = modelMapper.map(user, UserDto.class);
	    return userDto;
	}

	private List<UserDto> calculatePartyBalance(List<UserDto> users, int party) {
		double totalExpenses = 0;
		double userBalance = 0;
		List<Expense> userExpenses = new ArrayList<>();
		List<Expense> expenses = expenseDao.getPartyExpenses(party);
		totalExpenses = expenses.stream().mapToDouble(o -> o.getCost()).sum();
        for (UserDto user : users) {
        	userExpenses.clear();
            userBalance = expenses.stream().filter(e -> e.getUsername().equals(user.getName())).mapToDouble(o -> o.getCost()).sum();
        	user.setBalance(userBalance - (totalExpenses/users.size()));
        	userBalance=0;
        }
		return users;
	}

}
