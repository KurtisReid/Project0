package dev.reid.doas;

import dev.reid.entity.Employee;
import dev.reid.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EmployeeDAOPostgres implements EmployeeDAO{
    @Override
    public Employee createEmployee(Employee employee) {
        //try with resources syntax. It automatically closes the connection at the end of the try or catch
        try(Connection conn = ConnectionUtil.createConnection())
        {

            String sql = "Insert into employee values (default, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //preparedStatement.setInt(1, employee.getId());
            preparedStatement.setString(1, employee.getName());


            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();// returns the id that was crewated
            rs.next(); // youi haver to move the cursor to the first valid record

            int generatedKey = rs.getInt("id");
            employee.setId(generatedKey);
            return employee;



        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public Employee getEmployeeByID(int id) {
        try(Connection connection = ConnectionUtil.createConnection())
        {


            String sql = "select * from employee where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();
            System.out.println(rs);
            if (!rs.next())
            {
                throw new RuntimeException();
            }

            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setName(rs.getString("name"));



            return employee;


        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Employee> getListOfEmployees() {
        try (Connection conn = ConnectionUtil.createConnection())
        {
            String sql = "select * from employee";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            List<Employee> employeeList = new ArrayList<>();

            while (rs.next())
            {
                Employee employee = new Employee();


                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employeeList.add(employee);



            }
            return employeeList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        try(Connection conn = ConnectionUtil.createConnection()) {
            //update book set title = 'the stranger things', author = 'Albert kamus', return_date = 1 where id = 1;
            String sql = "update employee set name = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


            //preparedStatement.setInt(1, employee.getId());
            preparedStatement.setString(1, employee.getName());

            preparedStatement.executeUpdate();
            return employee;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteEmployeeByID(int id) {
        try (Connection conn = ConnectionUtil.createConnection())
        {
            String sql = "delete from employee where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
