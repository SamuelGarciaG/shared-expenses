package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Expense;

public interface ExpenseJpa extends JpaRepository<Expense,Integer> {

}
