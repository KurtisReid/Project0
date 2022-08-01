package dev.reid.doas;

import dev.reid.entity.Employee;
import dev.reid.entity.Expense;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ExpenseDOALocal implements ExpenseDOA{

    private Map<Integer,Expense> expenseTable = new HashMap();
    private int idMaker = 1;
    @Override
    public Expense createExpense(Expense expense) {
        expense.setId(idMaker++);
        expenseTable.put(expense.getId(), expense);
        return expense;
    }

    @Override
    public Map<Integer,Expense> getExpensesByStatus(String status) {


        return null;
    }

    @Override
    public Expense getExpenseByEmployee(int employeeId) {
        return null;
    }

    @Override
    public Map<Integer,Expense>  getListOfExpenses() {
        return expenseTable;

    }

    @Override
    public boolean deleteExpenseByID(int id) {
        /*
        DELETE /expenses/19
returns a 404 if expense not found
return a 422 if expense is already approved or denied
Once approved or denied they CANNOT be deleted or edited


        */
        return false;
    }

    @Override
    public Expense getExpenseByID(int id) {
        return expenseTable.get(id);

    }

    @Override
    public Expense updateExpenseStatus(int id, String status) {
        status = status.toUpperCase();
        /*

        Once approved or denied they CANNOT be deleted or edited
         */
        Expense expense = expenseTable.get(id);
        //check if book == null
        if (expenseTable.get(id).equals(null))
        {
            // throw some error
            System.out.println("id not found");
            return null;
        }

        if (expense.getStatus() == "APPROVED" || expense.getStatus() == "DENIED")
        {
            System.out.println("Cannot edit Expense status");
            return null;
        }

        if (status == "APPROVED" || status == "DENIED" || status == "PENDING")
        {
            expense.setStatus(status);
            return expense;
        }
        else
        {
            System.out.println("invalid status input");
        }


        return null;
    }
}
