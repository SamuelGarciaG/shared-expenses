package dao;


import java.util.List;

import model.Expense;


public interface ExpenseDao {
	
	public List<Expense> getExpenses();
	public Expense addExpense(Expense expense);

}
