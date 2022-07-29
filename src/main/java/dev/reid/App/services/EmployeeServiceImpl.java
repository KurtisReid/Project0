package dev.reid.App.services;

import dev.reid.App.doas.EmployeeDAO;
import dev.reid.App.entity.Employee;

public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO)
    {
        this.employeeDAO = employeeDAO;
    }


    @Override
    public Employee registerEmployee(Employee employee) {
        if ()

        return null;
    }

    @Override
    public Employee retriveRemployeeByID(int id) {
        return null;
    }

    @Override
    public boolean deleteEmployee(int id) {
        return false;
    }

    @Override
    public Employee modifyEmployee(Employee employee) {
        return null;
    }
}
