package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Expense;

public interface ExpenseJpa extends JpaRepository<Expense,Integer> {

	List<Expense> findByusername(String name);
	List<Expense> findBygroup(int group);

}
