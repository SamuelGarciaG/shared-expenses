package com.autentia.sharedexpenses;

import controller.Controller;
import dto.UserDto;
import model.Expense;
import model.Party;
import model.User;
import services.ExpenseService;
import services.PartyService;
import services.UserService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.CoreMatchers.equalTo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith({ MockitoExtension.class })
public class ControllerTest {

	Controller controller;
	@Mock
	private ExpenseService expenseService;
	@Mock
	private UserService userService;
	@Mock
	private PartyService partyService;

	List<UserDto> users = new ArrayList<>();
	List<User> usersNoDto = new ArrayList<>();
	List<Expense> expenses = new ArrayList<>();
	List<Party> partys = new ArrayList<>();

	@BeforeEach
	void setUp() throws ParseException {
		
		controller = new Controller(expenseService, userService, partyService);

		UserDto samu = new UserDto(); UserDto patri = new UserDto();
		samu.setFidparty(0); samu.setName("samu"); samu.setBalance(0); samu.setIduser(0);
		patri.setFidparty(0); patri.setName("patri"); patri.setBalance(0); patri.setIduser(1);
		users.add(samu); users.add(patri);

		Expense samuExpense1 = new Expense(); Expense samuExpense2 = new Expense(); Expense patriExpense1 = new Expense();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		String dateInString = "24-09-2020 12:48:00";
		Date date; date = sdf.parse(dateInString);
		samuExpense1.setConcept("concepto1"); samuExpense1.setCost(100); samuExpense1.setDate(date); samuExpense1.setFidparty(0); samuExpense1.setIdexpense(0); samuExpense1.setUsername("samu");
		samuExpense2.setConcept("concepto2"); samuExpense2.setCost(10); samuExpense2.setDate(date); samuExpense1.setFidparty(0); samuExpense2.setIdexpense(1); samuExpense2.setUsername("samu");
		patriExpense1.setConcept("concepto3"); patriExpense1.setCost(10); patriExpense1.setDate(date); patriExpense1.setFidparty(0); patriExpense1.setIdexpense(2); patriExpense1.setUsername("patri");
		expenses.add(samuExpense1); expenses.add(samuExpense2); expenses.add(patriExpense1);

		Party party0 = new Party();
		User aux = new User(); aux.setFidparty(0); aux.setIduser(0); aux.setName("samu");
		usersNoDto.add(aux);
		party0.setIdParty(0); party0.setUsers(usersNoDto);
		partys.add(party0);
	}

	@Test
	void whenGetUsersShouldReturnListOfUsersDto() {
		Mockito.when(userService.getUsers()).thenReturn(users);
		assertThat(users, equalTo(controller.getUsers()));
	}

	@Test
	void whenGetExpensesShouldReturnListOfExpenses() {
		Mockito.when(expenseService.getExpenses()).thenReturn(expenses);
		assertThat(expenses, equalTo(controller.getExpenses()));
	}


}
