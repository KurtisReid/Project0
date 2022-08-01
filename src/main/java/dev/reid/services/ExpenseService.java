package dev.reid.services;

import dev.reid.entity.Employee;
import dev.reid.entity.Expense;

import java.util.Map;

public interface ExpenseService {

    Expense registerExpense(Expense expense);

    Expense retriveExpenseByID(int id);

    boolean deleteExpense(int id);

    Expense modifyExpense(Expense expense);

    Map<Integer,Expense> returnAllExpenses();
}
