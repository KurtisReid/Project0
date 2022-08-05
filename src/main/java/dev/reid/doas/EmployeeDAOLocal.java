package dev.reid.doas;

import dev.reid.entity.Employee;

import java.util.*;

public class EmployeeDAOLocal implements EmployeeDAO{

    private Map<Integer, Employee> employeeTable = new HashMap();

    private int idMaker = 1;

    @Override
    public Employee createEmployee(Employee employee) {
        //System.out.println(employee.getName());
        employee.setId(idMaker++);
        employeeTable.put(employee.getId(), employee);
        //System.out.println(employeeTable.values());
        return employee;
    }

    @Override
    public Employee getEmployeeByID(int id) {
        System.out.println(employeeTable.values());
        return employeeTable.get(id);
    }

    @Override
    public List<Employee> getListOfEmployees() {
        List<Employee> employees = new ArrayList(this.employeeTable.values());
        return employees;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        //System.out.println(employeeTable.get(employee.getId()));
        employeeTable.put(employee.getId(), employee);
        //System.out.println(employeeTable.get(employee.getId()));
        //System.out.println(employee);
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
