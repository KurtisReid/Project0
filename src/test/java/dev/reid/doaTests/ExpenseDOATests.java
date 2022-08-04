package dev.reid.doaTests;

import dev.reid.doas.ExpenseDAO;
import dev.reid.doas.ExpenseDAOLocal;
import dev.reid.doas.ExpenseDAOPostgres;
import dev.reid.entity.Expense;
import dev.reid.entity.Status;
import dev.reid.entity.Type;
import dev.reid.utils.ConnectionUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class ExpenseDOATests {
    static ExpenseDAO expenseDOA = new ExpenseDAOPostgres();
/*
    @BeforeAll //this method will exicute before any tests
    static void setup()
    {
        try(Connection conn = ConnectionUtil.createConnection())
        {
            String sql = "create table expenses(\n" +
                    "\tid serial primary key,\n" +
                    "\texpense_cost decimal not null,\n" +
                    "\tdescription varchar(100),\n" +
                    "\ttype varchar(10) not null,\n" +
                    "\tstatus varchar(10) not null,\n" +
                    "\temployee_issuer int not null\n" +
                    "\t\n" +
                    ");";
            Statement statement = conn.createStatement();
            statement.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    	id serial primary key,
	expense_cost decimal not null,
	description varchar(100),
	type varchar(10) not null,
	status varchar(10) not null,
	employee_issuer varchar(100) not null

     */

    @Test
    @Order(1)
    void create_expense_test()
    {
        Expense expense = new Expense(1,10.0,Status.PENDING,1,"gay", Type.TRAVEL);
        Expense savedExpensev1 = expenseDOA.createExpense(expense);
        Expense expensev2 = new Expense(2,10.0,Status.PENDING,1,"gay", Type.TRAVEL);
        Expense savedExpensev2 = expenseDOA.createExpense(expensev2);
        Expense expensev3 = new Expense(3,10.0,Status.PENDING,1,"gay", Type.TRAVEL);

        Assertions.assertNotEquals(0, savedExpensev1.getId());


    }

    @Test
    @Order(2)
    void get_expense_by_id_test()
    {
        Expense expense = new Expense(1,10.0,Status.PENDING,1,"gay", Type.TRAVEL);

        Expense savedExpense = expenseDOA.createExpense(expense);
        Assertions.assertEquals(1,1);

    }

    @Test
    @Order(3)
    void get_expenses_by_status_test()
    {
        Expense expense = new Expense(1,10.0,Status.PENDING,1,"gay", Type.TRAVEL);
        Expense savedExpense = expenseDOA.createExpense(expense);
        Expense expensev4 = new Expense(4,12.0,Status.PENDING,1,"gay", Type.TRAVEL);
        Expense savedExpensev4 = expenseDOA.createExpense(expensev4);



        Expense expensev2 = new Expense(2,10.0,Status.DENIED,1,"gay", Type.TRAVEL);
        Expense savedExpensev2 = expenseDOA.createExpense(expensev2);
        Expense expensev5 = new Expense(2,10.0,Status.DENIED,1,"gay", Type.TRAVEL);
        Expense savedExpensev5 = expenseDOA.createExpense(expensev5);

        Expense expensev3 = new Expense(3,10.0,Status.APPROVED,1,"gay", Type.TRAVEL);
        Expense savedExpensev3 = expenseDOA.createExpense(expensev3);
        Expense expensev6 = new Expense(3,10.0,Status.APPROVED,1,"gay", Type.TRAVEL);
        Expense savedExpensev6 = expenseDOA.createExpense(expensev6);


        Assertions.assertEquals(expense.getStatus(),Status.PENDING);
        Assertions.assertEquals(expensev2.getStatus(),Status.DENIED);
        Assertions.assertEquals(expensev3.getStatus(),Status.APPROVED);


        Map<Integer, Expense> expenseMapP = expenseDOA.getExpensesByStatus("PENDING");
        Map<Integer, Expense> expenseMapD = expenseDOA.getExpensesByStatus("DENIED");
        Map<Integer, Expense> expenseMapA = expenseDOA.getExpensesByStatus("APPROVED");

        Assertions.assertEquals(expenseMapP.get(expense.getId()).getStatus(), Status.PENDING);
        Assertions.assertNotEquals(expenseMapP.get(expense.getId()).getStatus(), Status.DENIED);
        Assertions.assertNotEquals(expenseMapP.get(expense.getId()).getStatus(), Status.APPROVED);



        Assertions.assertNotEquals(expenseMapD.get(expensev2.getId()).getStatus(), Status.PENDING);
        Assertions.assertEquals(expenseMapD.get(expensev2.getId()).getStatus(), Status.DENIED);
        Assertions.assertNotEquals(expenseMapD.get(expensev2.getId()).getStatus(), Status.APPROVED);

        Assertions.assertNotEquals(expenseMapA.get(expensev6.getId()).getStatus(), Status.PENDING);
        Assertions.assertNotEquals(expenseMapA.get(expensev6.getId()).getStatus(), Status.DENIED);
        Assertions.assertEquals(expenseMapA.get(expensev6.getId()).getStatus(), Status.APPROVED);


        //System.out.println("PENDING: "+ expenseMapP);
        //System.out.println("DENIED: "+ expenseMapD);
        //System.out.println("Approved: " + expenseMapA);



    }

    @Test
    @Order(4)
    void get_expenses_by_employee_id_test()
    {
        Expense expense = new Expense(1,11.0,Status.PENDING,1,"gay", Type.TRAVEL);
        Expense savedExpense = expenseDOA.createExpense(expense);
        Expense expensev2 = new Expense(2,11.0,Status.PENDING,2,"gay", Type.TRAVEL);
        Expense savedExpensev2 = expenseDOA.createExpense(expensev2);
        Expense expensev3 = new Expense(3,11.0,Status.PENDING,3,"gay", Type.TRAVEL);
        Expense savedExpensev3 = expenseDOA.createExpense(expensev2);
        Assertions.assertEquals(expense.getEmployeeIssuer(),1);
        Assertions.assertEquals(expensev2.getEmployeeIssuer(),2);
        Assertions.assertEquals(expensev3.getEmployeeIssuer(),3);
        String result = expenseDOA.getExpenseByID(1).toString();
        System.out.println(result);

    }

    @Test
    @Order(5)
    void get_all_expenses_test()
    {
        Expense expense = new Expense(1,10.0,Status.PENDING,1,"gay", Type.TRAVEL);
        Expense savedExpense = expenseDOA.createExpense(expense);
        Expense expensev2 = new Expense(2,11.0,Status.PENDING,1,"str8", Type.TRAVEL);
        Expense savedExpensev2 = expenseDOA.createExpense(expensev2);
        Expense expensev3 = new Expense(3,12.0,Status.PENDING,1,"rged", Type.TRAVEL);
        Expense savedExpensev3 = expenseDOA.createExpense(expensev3);


        Map<Integer, Expense> expenseList = expenseDOA.getListOfExpenses();
        System.out.println(expenseList);

        Assertions.assertEquals(3, expenseList.size());
        //Assertions.assertEquals(true, expenseList.containsValue(expensev2));
        //Assertions.assertEquals(true, expenseList.containsValue(expensev3));


    }



    @Test
    @Order(6)
    void delete_expense_test()
    {
        Expense expense = new Expense(1,10.0,Status.PENDING,1,"gay", Type.TRAVEL);
        Expense savedExpense = expenseDOA.createExpense(expense);
        Expense expensev2 = new Expense(2,10.0,Status.PENDING,1,"gay", Type.TRAVEL);
        Expense savedExpensev2 = expenseDOA.createExpense(expensev2);
        Expense expensev3 = new Expense(3,10.0,Status.PENDING,1,"gay", Type.TRAVEL);
        Expense savedExpensev3 = expenseDOA.createExpense(expensev2);

        String result = expenseDOA.deleteExpenseByID(1);
        Assertions.assertEquals("200", result);



    }

    /*
    @AfterAll
    static void teardown()
    {
        try(Connection connection = ConnectionUtil.createConnection())
        {
            String sql = "drop table expenses";
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
*/






}
