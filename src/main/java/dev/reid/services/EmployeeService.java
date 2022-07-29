package dev.reid.services;

import dev.reid.entity.Employee;

public interface EmployeeService {

    Employee registerEmployee(Employee employee);

    Employee retriveRemployeeByID(int id);

    boolean deleteEmployee(int id);

    Employee modifyEmployee(Employee employee);
}
