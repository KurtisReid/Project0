package dev.reid.doaTests;

import dev.reid.App.doas.EmployeeDAO;
import dev.reid.App.doas.EmployeeDAOLocal;
import dev.reid.App.entity.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmployeeDOATests {
    static EmployeeDAO employeeDAO = new EmployeeDAOLocal();

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
        Employee employee = employeeDAO.getEmployeeByID(1);
        Assertions.assertEquals("Matt", employee.getName());

    }

    @Test
    void update_employee_test()
    {
        Employee employeev2 = new Employee(1, "John");
        employeeDAO.updateEmployee(employeev2);
        Employee employee = employeeDAO.getEmployeeByID(1);
        Assertions.assertEquals("John", employee.getName());
    }

    @Test
    void delete_employee_test()
    {
        boolean result = employeeDAO.deleteEmployeeByID(1);
        Assertions.assertTrue(result);
    }

}
