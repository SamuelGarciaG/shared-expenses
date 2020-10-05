package services;

import java.util.List;

import model.Expense;

public interface ExpenseService {
	
	public List<Expense> getExpenses();
	public Expense addExpense(Expense expense);
	public List<Expense> getUserExpenses(String user);
	public List<Expense> getGroupExpenses(int group);

}
