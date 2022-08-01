package dev.reid.doas;

import dev.reid.entity.Employee;
import dev.reid.entity.Expense;

import java.util.Map;
import java.util.Set;

public interface ExpenseDOA {

    Expense createExpense(Expense expense);//post POST /expenses
    Map<Integer,Expense> getExpensesByStatus(String status);//get
    Expense getExpenseByEmployee(int employeeId);// get
    Map<Integer,Expense> getListOfExpenses();//get GET /expenses
    boolean deleteExpenseByID(int id);//delete
    Expense getExpenseByID(int id);//get
    Expense updateExpenseStatus(int id, String status);// PATCH /expenses/20/deny




}
