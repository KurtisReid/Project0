package dev.reid.App.doas;

import dev.reid.App.entity.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<Employee> getListOfEmployees() {
        return null;
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
