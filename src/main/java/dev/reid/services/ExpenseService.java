package dev.reid.services;

import dev.reid.entity.Employee;
import dev.reid.entity.Expense;
import dev.reid.entity.Status;

import java.util.Map;
import java.util.Set;

public interface ExpenseService {

    Expense registerExpense(Expense expense);

    Expense retriveExpenseByID(int id);

    String deleteExpense(int id);

    Expense modifyExpense(Expense expense);

    Map<Integer,Expense> returnAllExpenses();

    Map<Integer,Expense> getExpensesByStatus(Status status);

    Map<Integer,Expense> getExpenseByEmployee(int employeeId);


}
