package dev.reid.App.services;

import dev.reid.App.entity.Employee;

public interface EmployeeService {

    Employee registerEmployee(Employee employee);

    Employee retriveRemployeeByID(int id);

    boolean deleteEmployee(int id);

    Employee modifyEmployee(Employee employee);
}
