package dev.reid.doas;

import dev.reid.App.App;
import dev.reid.entity.Expense;
import dev.reid.entity.Status;
import dev.reid.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
create table expenses(
	id serial primary key,
	expense_cost decimal not null,
	description varchar(100),
	type varchar(10) not null,
	status varchar(10) not null,
	employee_issuer varchar(100) not null

);

 */
public class ExpenseDAOPostgres implements ExpenseDAO{
    @Override
    public Expense createExpense(Expense expense) {
        //try with resources syntax. It automatically closes the connection at the end of the try or catch
        try(Connection conn = ConnectionUtil.createConnection())
        {
            //int id, double expenseCost, Status status, int employeeIssuer, String desc

            String sql = "Insert into expenses values (default, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //preparedStatement.setInt(1, expense.getId());
            preparedStatement.setDouble(1, expense.getExpenseCost());
            preparedStatement.setString(2, expense.getDesc());
            preparedStatement.setString(3, expense.getType().toString());
            preparedStatement.setString(4, expense.getStatus().toString());
            preparedStatement.setInt(5, expense.getEmployeeIssuer());

            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();// returns the id that was crewated
            rs.next(); // youi haver to move the cursor to the first valid record

            int generatedKey = rs.getInt("id");
            expense.setId(generatedKey);
            //System.out.println("EXpense" + expense);
            return expense;



        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<Integer, Expense> getExpensesByStatus(String status) {
        return null;
    }

    @Override
    public Map<Integer, Expense> getExpenseByEmployee(int employeeId) {

        Map<Integer, Expense> expenseList = new HashMap();
        try(Connection connection = ConnectionUtil.createConnection())
        {
            //int id, double expenseCost, Status status, int employeeIssuer, String desc
            String sql = "select * from expenses where employee_issuer = ?";//CHANGE THIS
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(4,employeeId);

            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Expense expense = new Expense();
                expense.setId(rs.getInt("id"));
                expense.setEmployeeIssuer(rs.getInt("employee_issuer"));



                if (expense.getEmployeeIssuer() == employeeId)
                {
                    expense.setExpenseCost(rs.getDouble("expense_cost"));
                    expense.setStatus(rs.getString("status"));
                    expense.setDesc(rs.getString("description"));
                    expense.setType(rs.getString("type"));
                    expenseList.put(expense.getId(), expense);
                }

            }




            return expenseList;


        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        //return null;

    }

    @Override
    public Map<Integer, Expense> getListOfExpenses() {
        try (Connection conn = ConnectionUtil.createConnection())
        {
            String sql = "select * from expenses";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            Map<Integer, Expense> expenseList = new HashMap();
            int tempCount = 0;


//(1,10.0,Status.PENDING,1,"gay", Type.TRAVEL);
            while (rs.next())
            {
                System.out.println(tempCount);

                Expense expense = new Expense();
                expense.setId(rs.getInt("id"));
                expense.setExpenseCost(rs.getDouble("expense_cost"));
                expense.setStatus(rs.getString("status").toUpperCase());
                expense.setEmployeeIssuer(rs.getInt("employee_issuer"));
                expense.setDesc(rs.getString("description"));
                expense.setType(rs.getString("type"));
                expenseList.put(expense.getId(), expense);
                tempCount++;

            }
            return expenseList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String deleteExpenseByID(int id) {
        /*
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

        return "202";
        */
        try (Connection conn = ConnectionUtil.createConnection())
        {
            //getting expense to check its status
            Expense expense = new Expense();
            expense = getExpenseByID(id);
            System.out.println(expense);



            if (expense == null)
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
            else
            {
                //sucessful
                //delete expense
                String sql = "delete from expenses where id = ?";
                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setInt(1, id);
                ps.execute();
                return "200";
            }


        } catch (SQLException e) {
            e.printStackTrace();
            return "issue with delete";
        }
        //return "issue with delete v2";

    }

    @Override
    public Expense getExpenseByID(int id) {
        try(Connection connection = ConnectionUtil.createConnection())
        {

            //int id, double expenseCost, Status status, int employeeIssuer, String desc
            String sql = "select * from expenses where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();
            rs.next();

                Expense expense = new Expense();
                expense.setId(rs.getInt("id"));
                if (expense.getId() == id)
                {
                    expense.setExpenseCost(rs.getDouble("expense_cost"));
                    //expense.setStatus(rs.getString("status"));
                    expense.setEmployeeIssuer(rs.getInt("employee_issuer"));
                    expense.setDesc(rs.getString("description"));
                    return expense;
                }








        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return null;

    }

    @Override
    public Expense updateExpenseStatus(int id, String status)
    {
        /*

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

         */

        try(Connection conn = ConnectionUtil.createConnection()) {
            //int id, double expenseCost, Status status, int employeeIssuer, String desc

            String sql = "update expense set expense_cost = ?, description  = ?, type = ?,  status = ?, employee_issuer = ? where id = ?";

            Expense expense = new Expense();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, expense.getId());
            preparedStatement.setDouble(2, expense.getExpenseCost());
            preparedStatement.setString(3, expense.getDesc());
            preparedStatement.setString(4, expense.getType().toString());
            preparedStatement.setString(5, expense.getStatus().toString());

            System.out.println(expense.getStatus().toString());
            if (expense.getStatus() == Status.APPROVED || expense.getStatus() == Status.DENIED)
            {
                System.out.println("Cannot edit Expense status");
                return null;
            }

                preparedStatement.setInt(6, expense.getEmployeeIssuer());
                preparedStatement.setString(5, status.toString());
                preparedStatement.executeUpdate();
                expense.setStatus(status);
                return expense;




        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
