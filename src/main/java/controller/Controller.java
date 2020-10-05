package controller;

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
import model.User;
import services.ExpenseService;
import services.UserService;

@RestController
public class Controller {
	
	ExpenseService expenseService;
	UserService userService;
	ModelMapper modelMapper;
	
	public Controller(@Autowired ExpenseService expenseService, @Autowired UserService userService, @Autowired ModelMapper modelMapper) {
		this.expenseService=expenseService;
		this.userService=userService;
		this.modelMapper=modelMapper;
	}
	
	@GetMapping (value ="expenses", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Expense> getExpenses() {
		return expenseService.getExpenses();
	}
	
	@GetMapping (value ="expenses/user/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Expense> getUserExpenses(@PathVariable("user") String user) {
		return expenseService.getUserExpenses(user);
	}
	
	@GetMapping (value ="expenses/group/{group}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Expense> getGroupExpenses(@PathVariable("group") int group) {
		return expenseService.getGroupExpenses(group);
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

	@GetMapping (value ="users/group/{group}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserDto> getGroupUsers(@PathVariable("group") int group){
        List<User> users = userService.getUsersByGroup(group);
        List<UserDto> usersDto = users.stream().map(this::convertToDto).collect(Collectors.toList());
        for (UserDto userDto : usersDto) {
            userDto.setBalance(calculateBalance(userDto));
        }
        return usersDto;
	}
	
	@PostMapping (value ="users", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public User addUser(@RequestBody UserDto userDto) {
		return convertToEntity(userDto);
	}
	
	private int calculateBalanceGroup(int group) {
		int balance = 0;
		List<Expense> expenses = getGroupExpenses(group);
        for (Expense expense : expenses) {
            balance+=expense.getCost();
        }
		return balance;
	}
	
	//prueba sin grupo
	private int calculateBalance(UserDto userDto) {
		int balance = 0;
		List<Expense> expenses = getUserExpenses(userDto.getName());
        for (Expense expense : expenses) {
            balance+=expense.getCost();
        }
		return balance;
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
