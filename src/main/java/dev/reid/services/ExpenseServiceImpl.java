package dev.reid.services;

import dev.reid.doas.ExpenseDOA;
import dev.reid.entity.Expense;
import dev.reid.entity.Status;

import java.util.Map;
import java.util.Set;

public class ExpenseServiceImpl implements ExpenseService{

    private ExpenseDOA expenseDOA;
    @Override
    public Expense registerExpense(Expense expense) {
        return this.expenseDOA.createExpense(expense);

    }

    @Override
    public Expense retriveExpenseByID(int id) {
        return this.expenseDOA.getExpenseByID(id);

    }

    @Override
    public String deleteExpense(int id) {
        return this.expenseDOA.deleteExpenseByID(id);

    }

    @Override
    public Expense modifyExpense(Expense expense) {
        return this.expenseDOA.updateExpenseStatus(expense.getId(), expense.getStatus());

    }

    @Override
    public Map<Integer,Expense> returnAllExpenses() {
        return this.expenseDOA.getListOfExpenses();

    }
    @Override
    public Map<Integer,Expense> getExpensesByStatus(Status status)
    {
        return this.getExpensesByStatus(status);
    }

    @Override
    public Map<Integer, Expense> getExpenseByEmployee(int employeeId) {
        return this.expenseDOA.getExpenseByEmployee(employeeId);
    }
}
