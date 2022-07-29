package dev.reid.doas;

import dev.reid.entity.Employee;

import java.util.*;

public class EmployeeDAOLocal implements EmployeeDAO{

    private Map<Integer, Employee> employeeTable = new HashMap();

    private int idMaker = 1;

    @Override
    public Employee createEmployee(Employee employee) {
        employee.setId(idMaker++);
        employeeTable.put(employee.getId(), employee);
        return employee;
    }

    @Override
    public Employee getEmployeeByID(int id) {
        return employeeTable.get(id);
    }

    @Override
    public Set<Employee> getListOfEmployees() {
        Set<Employee> employees = new HashSet<Employee>(this.employeeTable.values());
        return employees;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        employeeTable.put(employee.getId(), employee);
        return employee;
    }

    @Override
    public boolean deleteEmployeeByID(int id) {
        Employee employee = employeeTable.remove(id);
        if (employee == null)
        {
            return false;
        }
        return true;
    }
}
