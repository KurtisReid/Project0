package dev.reid.App.doas;

import dev.reid.App.entity.Employee;

import java.util.List;
import java.util.Set;

public interface EmployeeDAO {

    Employee createEmployee(Employee employee); //POST

    Employee getEmployeeByID(int id); //GET

   Set<Employee> getListOfEmployees(); // GET with id

   Employee updateEmployee(Employee employee); // Put

   boolean deleteEmployeeByID(int id); // delte employee
}
