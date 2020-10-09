package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dto.UserDto;
import model.Expense;
import model.Party;
import services.ExpenseService;
import services.PartyService;
import services.UserService;

@RestController
public class Controller {
	
	ExpenseService expenseService;
	UserService userService;
	PartyService partyService;

	
	public Controller(@Autowired ExpenseService expenseService, @Autowired UserService userService, @Autowired PartyService partyService) {
		this.expenseService=expenseService;
		this.userService=userService;
		this.partyService=partyService;
	}

	@GetMapping (value ="partys", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Party> getPartys() {
		return partyService.getPartys();
	}

	@GetMapping (value ="partys/{idParty}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Party getPartyById(@PathVariable("idParty") int party) {
		return partyService.getPartyById(party);
	}

	@GetMapping (value ="partys/{idParty}/balance", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserDto> getPartyUserBalanceById(@PathVariable("idParty") int party) {
		return partyService.getPartyBalance(party);
	}
	
	@GetMapping (value ="partys/{idParty}/expenses", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Expense> getPartyExpensesById(@PathVariable("idParty") int party) {
		return expenseService.getPartyExpenses(party);
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
        return userService.getUsers();
	} 

	@PostMapping (value ="users", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserDto addUser(@RequestBody UserDto userDto) {
		return userService.addUser(userDto);
	}

}
