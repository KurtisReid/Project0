package dev.reid.App.doas;

import dev.reid.App.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    Employee createEmployee(Employee employee); //POST

    Employee getEmployeeByID(int id); //GET

   List<Employee> getListOfEmployees(); // GET with id

   Employee updateEmployee(Employee employee); // Put

   boolean deleteEmployeeByID(int id); // delte employee
}
