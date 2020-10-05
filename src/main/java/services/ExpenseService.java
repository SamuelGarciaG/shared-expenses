package services;

import java.util.List;

import model.Expense;

public interface ExpenseService {
	
	public List<Expense> getExpenses();
	public Expense addExpense(Expense expense);

}
