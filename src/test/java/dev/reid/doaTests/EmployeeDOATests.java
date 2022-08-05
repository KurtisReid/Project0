package dev.reid.doaTests;

import dev.reid.doas.EmployeeDAO;
import dev.reid.doas.EmployeeDAOLocal;
import dev.reid.doas.EmployeeDAOPostgres;
import dev.reid.entity.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

public class EmployeeDOATests {
    static EmployeeDAO employeeDAO = new EmployeeDAOLocal(); // NOTE: EmployeeDAOPostgres not yet working

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
        Employee employeev2 = new Employee(1, "John");
        Employee employee1 = employeeDAO.updateEmployee(employeev2);
        Employee employee = employeeDAO.getEmployeeByID(employee1.getId());
        Assertions.assertEquals("John", employee.getName());
    }

    @Test
    void get_all_employees()
    {
        /*
        Map<Integer, Expense> expenseList = expenseDOA.getListOfExpenses();
        int size = expenseList.size();

        Expense expense = new Expense(1,10.0,Status.PENDING,1,"sv", Type.TRAVEL);
        Expense savedExpense = expenseDOA.createExpense(expense);
        Expense expensev2 = new Expense(2,11.0,Status.PENDING,1,"grw", Type.TRAVEL);
        Expense savedExpensev2 = expenseDOA.createExpense(expensev2);
        Expense expensev3 = new Expense(3,12.0,Status.PENDING,1,"rged", Type.TRAVEL);
        Expense savedExpensev3 = expenseDOA.createExpense(expensev3);
        Map<Integer, Expense> expenseList1 = expenseDOA.getListOfExpenses();



        System.out.println(expenseList.size());

        Assertions.assertEquals(size+3, expenseList1.size());
        //Assertions.assertEquals(true, expenseList.containsValue(expensev2));
        //Assertions.assertEquals(true, expenseList.containsValue(expensev3));
         */
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

}
