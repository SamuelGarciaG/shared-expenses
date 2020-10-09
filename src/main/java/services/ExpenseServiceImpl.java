package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ExpenseDao;
import model.Expense;

@Service
public class ExpenseServiceImpl implements ExpenseService {
	
	ExpenseDao dao;
	
	public ExpenseServiceImpl(@Autowired ExpenseDao dao) {
		this.dao=dao;
	}

	@Override
	public List<Expense> getExpenses() {
		return dao.getExpenses();
	}

	@Override
	public Expense addExpense(Expense expense) {
		return dao.addExpense(expense);
	}

	@Override
	public List<Expense> getUserExpenses(String user) {
		return dao.getUserExpenses(user);
	}

	@Override
	public List<Expense> getPartyExpenses(int party) {
		return dao.getPartyExpenses(party);
	}
	
}
