package dev.reid.daoTests;

import dev.reid.doas.EmployeeDAO;
import dev.reid.doas.EmployeeDAOPostgres;
import dev.reid.entity.Employee;
import dev.reid.utils.ConnectionUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class EmployeeDAOTests {
    static EmployeeDAO employeeDAO = new EmployeeDAOPostgres(); // NOTE: EmployeeDAOPostgres not yet working

    @Test
    void create_employee_test()
    {
        Employee employee = new Employee(1,"Matt");
        Employee savedEmployee = employeeDAO.createEmployee(employee);
        Assertions.assertNotEquals(0, savedEmployee.getId());
    }



    @Test
    void get_employee_by_id_test()
    {
        Employee employee1 = new Employee(1,"Matt");
        System.out.println("matt: " + employee1.getName());
        Employee savedEmployee = employeeDAO.createEmployee(employee1);
        Employee employee = employeeDAO.getEmployeeByID(employee1.getId());
        System.out.println("Hello: " + employee);
        Assertions.assertEquals("Matt", employee.getName());

    }

    @Test
    void update_employee_test()
    {
        Employee employeeOriginal = new Employee(1, "Im special, but not 6557 times repeating in a table special. Why am i like this?"); // creating new employee
        employeeOriginal = employeeDAO.createEmployee(employeeOriginal); //putting employee in table
        System.out.println(employeeDAO.getListOfEmployees());
        System.out.println(employeeOriginal.getId());
        Employee employeeNew = new Employee(employeeOriginal.getId(), "John"); //creating employee to update employeeOriginal
        Employee employee1 = employeeDAO.updateEmployee(employeeNew); // updating employee
        System.out.println(employeeDAO.getListOfEmployees());

        Employee employee = employeeDAO.getEmployeeByID(employee1.getId());// getting employee from table to check name

        Assertions.assertEquals("John", employee.getName());
    }

    @Test
    void get_all_employees()
    {

        List<Employee> preEmployeeList = employeeDAO.getListOfEmployees();
        int size = preEmployeeList.size();

        Employee employee = new Employee(1,"Matt");
        Employee savedEmployee = employeeDAO.createEmployee(employee);
        Employee employeev2 = new Employee(2, "John");
        Employee savedEmployee2 = employeeDAO.createEmployee(employeev2);

        employeeDAO.updateEmployee(employeev2);

        List<Employee> employeeList = employeeDAO.getListOfEmployees();
        System.out.println(employeeList);

        Assertions.assertEquals(size + 2, employeeList.size());
        //Assertions.assertEquals(true, employeeList.contains(employeev2));
    }
    @Test
    void delete_employee_test()
    {
        boolean result = employeeDAO.deleteEmployeeByID(1);
        Assertions.assertTrue(result);
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
