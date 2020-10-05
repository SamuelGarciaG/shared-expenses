package controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Expense;
import model.User;
import services.ExpenseService;

@RestController
public class Controller {
	
	ExpenseService service;
	ModelMapper modelMapper;
	
	public Controller(@Autowired ExpenseService service, @Autowired ModelMapper modelMapper) {
		this.service=service;
		this.modelMapper=modelMapper;
	}
	
	@GetMapping (value ="expenses", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Expense> getExpenses () {
		return service.getExpenses();
	}
	
	@SuppressWarnings("unused")
	private UserDto convertToDto(User user) {
	    UserDto userDto = modelMapper.map(user, UserDto.class);
	    return userDto;
	}
	@SuppressWarnings("unused")
	private User convertToEntity(UserDto userDto) {
	    User user = modelMapper.map(userDto, User.class);
	    return user;
	    
	}

}
