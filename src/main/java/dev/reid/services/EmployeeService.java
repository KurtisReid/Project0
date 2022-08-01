package dev.reid.services;

import dev.reid.entity.Employee;
import dev.reid.entity.Expense;

import java.util.Map;
import java.util.Set;

public interface EmployeeService {

    Employee registerEmployee(Employee employee);

    Employee retriveRemployeeByID(int id);

    boolean deleteEmployee(int id);

    Employee modifyEmployee(Employee employee);

    Set<Employee> returnAllEmployees();


}
