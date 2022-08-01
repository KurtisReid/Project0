package dev.reid.doas;

import dev.reid.entity.Employee;
import dev.reid.entity.Expense;
import dev.reid.entity.Status;

import java.util.Map;
import java.util.Set;

public interface ExpenseDOA {

    Expense createExpense(Expense expense);//post POST /expenses
    Map<Integer,Expense> getExpensesByStatus(String status);//get
    Map<Integer,Expense> getExpenseByEmployee(int employeeId);// get
    Map<Integer,Expense> getListOfExpenses();//get GET /expenses
    String deleteExpenseByID(int id);//delete
    Expense getExpenseByID(int id);//get
    Expense updateExpenseStatus(int id, Status status);// PATCH /expenses/20/deny




}
