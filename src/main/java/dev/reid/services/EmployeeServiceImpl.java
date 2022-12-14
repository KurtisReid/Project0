package dev.reid.services;

import dev.reid.doas.EmployeeDAO;
import dev.reid.entity.Employee;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO)
    {
        this.employeeDAO = employeeDAO;
    }


    @Override
    public Employee registerEmployee(Employee employee) {
        System.out.println(employee);
        if (employee.getName().length() == 0)
        {
            throw new RuntimeException("i need a name");
        }

        Employee savedEmployee = this.employeeDAO.createEmployee(employee);

        return savedEmployee;
    }

    @Override
    public Employee retriveRemployeeByID(int id) {
        return this.employeeDAO.getEmployeeByID(id);
    }

    @Override
    public boolean deleteEmployee(int id) {
        return this.employeeDAO.deleteEmployeeByID(id);
    }

    @Override
    public Employee modifyEmployee(Employee employee) {
        System.out.println(employee.getId());
        //find employee by id
        //employee.setName(this.employeeDAO.getEmployeeByID(employee.getId()).getName());
        //System.out.println(employee.getName());
        if (employee.getName().length() == 0)
        {
            throw new RuntimeException("i need a name");
        }
        return this.employeeDAO.updateEmployee(employee);
    }
    @Override
    public List<Employee> returnAllEmployees()
    {
        return this.employeeDAO.getListOfEmployees();
    }
}
