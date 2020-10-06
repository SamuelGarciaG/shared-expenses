package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import model.Expense;
import model.Party;
import model.User;
import services.ExpenseService;
import services.PartyService;
import services.UserService;

@RestController
public class Controller {
	
	ExpenseService expenseService;
	UserService userService;
	PartyService partyService;
	ModelMapper modelMapper;
	
	public Controller(@Autowired ExpenseService expenseService, @Autowired UserService userService, @Autowired PartyService partyService, @Autowired ModelMapper modelMapper) {
		this.expenseService=expenseService;
		this.userService=userService;
		this.partyService=partyService;
		this.modelMapper=modelMapper;
	}
	
	@GetMapping (value ="expenses", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Expense> getExpenses() {
		return expenseService.getExpenses();
	}

	@PostMapping (value ="expenses", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Expense addExpense(@RequestBody Expense expense) {
		return expenseService.addExpense(expense);
	}
	
	@GetMapping (value ="users", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserDto> getUsers(){
        List<User> users = userService.getUsers();
        List<UserDto> usersDto = users.stream().map(this::convertToDto).collect(Collectors.toList());
        return usersDto;
	}

	@PostMapping (value ="users", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public User addUser(@RequestBody UserDto userDto) {
		return convertToEntity(userDto);
	}
	
	@GetMapping (value ="partys", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Party> getPartys() {
		return partyService.getPartys();
	}

	@GetMapping (value ="partys/{idParty}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Party getPartyById(@PathVariable("idParty") int party) {
		return partyService.getPartyById(party);
	}
	
	@GetMapping (value ="partys/{idParty}/expenses", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Expense> getPartyExpensesById(@PathVariable("idParty") int party) {
		return expenseService.getPartyExpenses(party);
	}
	
	@GetMapping (value ="partys/{idParty}/balance", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserDto> getPartyUserBalanceById(@PathVariable("idParty") int party) {
	       List<User> users = userService.getUsersByParty(party);
	       List<UserDto> usersDto = users.stream().map(this::convertToDto).collect(Collectors.toList());
		return calculateBalanceParty(usersDto, party);
	}

	private List<UserDto> calculateBalanceParty(List<UserDto> users, int party) {
		int totalExpenses = 0;
		int userBalance = 0;
		List<Expense> expenses = getPartyExpensesById(party);
        for (Expense expense : expenses) {
            totalExpenses+=expense.getCost();
        }
        for (UserDto user : users) {
        	List<Expense> userExpenses = new ArrayList<>();
            for (Expense expense : expenses) {
                if (expense.getUsername().equals(user.getName())) {
                	userExpenses.add(expense);
                }
            }
            for (Expense expense : userExpenses) {
            	userBalance+=expense.getCost();
            }
        	user.setBalance(userBalance - (totalExpenses/users.size()));
        	userBalance=0;
        }
		return users;
	}
	
	private UserDto convertToDto(User user) {
	    UserDto userDto = modelMapper.map(user, UserDto.class);
	    return userDto;
	}
	
	private User convertToEntity(UserDto userDto) {
	    User user = modelMapper.map(userDto, User.class);
	    return user;
	    
	}

}
