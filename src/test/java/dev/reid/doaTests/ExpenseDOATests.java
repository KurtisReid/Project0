package dev.reid.doaTests;

import dev.reid.doas.ExpenseDAO;
import dev.reid.doas.ExpenseDAOLocal;
import dev.reid.entity.Expense;
import dev.reid.entity.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class ExpenseDOATests {
    static ExpenseDAO expenseDOA = new ExpenseDAOLocal();

    @Test
    void create_expense_test()
    {
        Expense expense = new Expense(1,2.00, Status.PENDING, 1);
        Expense savedExpense = expenseDOA.createExpense(expense);
        Assertions.assertNotEquals(0, savedExpense.getId());


    }

    @Test
    void get_expense_by_id_test()
    {
        Expense expense = new Expense(1,2.00, Status.PENDING, 1);
        Expense savedExpense = expenseDOA.createExpense(expense);
        Assertions.assertEquals(1,1);

    }

    @Test
    void get_expenses_by_status_test()
    {
        Expense expense = new Expense(1,2.00, Status.PENDING, 1);
        Expense savedExpense = expenseDOA.createExpense(expense);
        Expense expensev2 = new Expense(2,2.00, Status.DENIED, 2);
        Expense savedExpensev2 = expenseDOA.createExpense(expensev2);
        Expense expensev3 = new Expense(3,2.00, Status.APPROVED, 3);
        Expense savedExpensev3 = expenseDOA.createExpense(expensev2);
        Assertions.assertEquals(expense.getStatus(),Status.PENDING);
        Assertions.assertEquals(expensev2.getStatus(),Status.DENIED);
        Assertions.assertEquals(expensev3.getStatus(),Status.APPROVED);

    }

    @Test
    void get_expenses_by_employee_id_test()
    {
        Expense expense = new Expense(1,2.00, Status.PENDING, 1);
        Expense savedExpense = expenseDOA.createExpense(expense);
        Expense expensev2 = new Expense(2,2.00, Status.DENIED, 2);
        Expense savedExpensev2 = expenseDOA.createExpense(expensev2);
        Expense expensev3 = new Expense(3,2.00, Status.APPROVED, 3);
        Expense savedExpensev3 = expenseDOA.createExpense(expensev2);
        Assertions.assertEquals(expense.getEmployeeIssuer(),1);
        Assertions.assertEquals(expensev2.getEmployeeIssuer(),2);
        Assertions.assertEquals(expensev3.getEmployeeIssuer(),3);

    }

    @Test
    void get_all_expenses_test()
    {
        Expense expense = new Expense(1,2.00, Status.PENDING, 1);
        Expense savedExpense = expenseDOA.createExpense(expense);
        Expense expensev2 = new Expense(2,2.00, Status.DENIED, 2);
        Expense savedExpensev2 = expenseDOA.createExpense(expensev2);
        Expense expensev3 = new Expense(3,2.00, Status.APPROVED, 3);
        Expense savedExpensev3 = expenseDOA.createExpense(expensev3);


        Map<Integer, Expense> expenseList = expenseDOA.getListOfExpenses();
        System.out.println(expenseList);

        Assertions.assertEquals(true, expenseList.containsValue(expense));
        Assertions.assertEquals(true, expenseList.containsValue(expensev2));
        Assertions.assertEquals(true, expenseList.containsValue(expensev3));


    }

    @Test
    void delete_expense_test()
    {
        Expense expense = new Expense(1,2.00, Status.PENDING, 1);
        Expense savedExpense = expenseDOA.createExpense(expense);
        Expense expensev2 = new Expense(2,2.00, Status.DENIED, 2);
        Expense savedExpensev2 = expenseDOA.createExpense(expensev2);
        Expense expensev3 = new Expense(3,2.00, Status.APPROVED, 3);
        Expense savedExpensev3 = expenseDOA.createExpense(expensev2);

        String result = expenseDOA.deleteExpenseByID(1);
        Assertions.assertEquals("200", result);



    }

}
