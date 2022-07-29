package dev.reid.App;

import dev.reid.doas.EmployeeDAOLocal;
import dev.reid.handlers.CreateEmployeeHandler;
import dev.reid.handlers.DeleteEmployeeHandler;
import dev.reid.services.EmployeeService;
import dev.reid.services.EmployeeServiceImpl;

public class App {

    public static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDAOLocal());

    public static void main(String[] args) {
        CreateEmployeeHandler createEmployeeHandler = new CreateEmployeeHandler();
        DeleteEmployeeHandler deleteEmployeeHandler = new DeleteEmployeeHandler();


    }
}
