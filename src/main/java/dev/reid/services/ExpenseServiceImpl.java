package dev.reid.services;

import dev.reid.doas.ExpenseDAO;
import dev.reid.doas.ExpenseDAOLocal;
import dev.reid.entity.Expense;
import dev.reid.entity.Status;

import java.util.Map;

public class ExpenseServiceImpl implements ExpenseService{

    private ExpenseDAO expenseDOA = new ExpenseDAOLocal();
    @Override
    public Expense registerExpense(Expense expense) {
        //System.out.println(expense);
        Expense exp = this.expenseDOA.createExpense(expense);
        return exp;

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
