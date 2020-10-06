package dao;


import java.util.List;

import model.Expense;


public interface ExpenseDao {
	
	public List<Expense> getExpenses();
	public Expense addExpense(Expense expense);
	public List<Expense> getUserExpenses(String user);
	public List<Expense> getPartyExpenses(int party);

}
