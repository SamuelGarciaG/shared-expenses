package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Expense;

@Repository
public class ExpenseDaoImpl implements ExpenseDao {
	
	ExpenseJpa jpa;
	
	public ExpenseDaoImpl(@Autowired ExpenseJpa jpa) {
		this.jpa = jpa;
	}

	@Override
	public List<Expense> getExpenses() {
		return jpa.findAll();
	}

	@Override
	public Expense addExpense(Expense expense) {
		return jpa.save(expense);
	}

}
