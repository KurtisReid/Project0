package dev.reid.doas;

import dev.reid.entity.Employee;
import dev.reid.entity.Expense;
import dev.reid.entity.Status;

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
    public Map<Integer,Expense> getExpenseByEmployee(int employeeId) {

        Map<Integer,Expense> returnMap = new HashMap();

        for (Map.Entry<Integer,Expense> entry : expenseTable.entrySet())
        {
            Expense expense = entry.getValue();
            if (expense.getEmployeeIssuer() == employeeId)
            {
                // assuming one to one rtealtionship
                returnMap.put(expense.getId(), expense);
            }
        }
        return returnMap;
    }

    @Override
    public Map<Integer,Expense>  getListOfExpenses() {
        return expenseTable;

    }

    @Override
    public String deleteExpenseByID(int id) {
        /*
        DELETE /expenses/19
returns a 404 if expense not found
return a 422 if expense is already approved or denied
Once approved or denied they CANNOT be deleted or edited


        */
        Expense expense = expenseTable.get(id);

        //check if book == null
        if (expenseTable.get(id).equals(null))
        {
            // throw some error
            System.out.println("id not found");

            return "404";
        }

        if (expense.getStatus() == Status.APPROVED || expense.getStatus() == Status.DENIED)
        {
            System.out.println("Cannot delete Expense status");
            return "422";
        }

        expense = expenseTable.remove(id);

        return "200";
    }

    @Override
    public Expense getExpenseByID(int id) {
        return expenseTable.get(id);

    }

    @Override
    public Expense updateExpenseStatus(int id, Status status) {

        /*

        Once approved or denied they CANNOT be deleted or edited
        // assuming that the id is unique
         */
        Expense expense = expenseTable.get(id);
        //check if book == null
        if (expenseTable.get(id).equals(null))
        {
            // throw some error
            System.out.println("id not found");
            return null;
        }

        if (expense.getStatus() == Status.APPROVED || expense.getStatus() == Status.DENIED)
        {
            System.out.println("Cannot edit Expense status");
            return null;
        }

        if (status == Status.APPROVED || status == Status.DENIED || status == Status.PENDING)
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
