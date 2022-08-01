package dev.reid.entity.App;

import dev.reid.doas.EmployeeDAOLocal;
import dev.reid.handlers.CreateEmployeeHandler;
import dev.reid.handlers.DeleteEmployeeHandler;
import dev.reid.handlers.GetEmployeeByIDHandler;
import dev.reid.handlers.UpdateEmployeeByIDHandler;
import dev.reid.services.EmployeeService;
import dev.reid.services.EmployeeServiceImpl;
import io.javalin.Javalin;
import io.javalin.http.Handler;

/*
POST /employees
returns a 201
GET /employees
GET /employees/120
returns a 404 if employee not found
PUT /employees/150
returns a 404 if employee not found
DELETE /employees/190
returns a 404 if employee not found
Expenses routes


 */
public class App {

    public static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDAOLocal());

    public static void main(String[] args) {
        Javalin app = Javalin.create();
        CreateEmployeeHandler createEmployeeHandler = new CreateEmployeeHandler();

        DeleteEmployeeHandler deleteEmployeeHandler = new DeleteEmployeeHandler();
        GetEmployeeByIDHandler getEmployeeByIDHandler = new GetEmployeeByIDHandler();
        UpdateEmployeeByIDHandler updateEmployeeByIDHandler = new UpdateEmployeeByIDHandler();



        app.post("/employees", createEmployeeHandler);
        //app.get("/employees", null);
        app.get("/employees/{id}", getEmployeeByIDHandler);
        app.put("/employees/{id}", updateEmployeeByIDHandler);
        app.delete("/employees/{id}", deleteEmployeeHandler);

        app.start();


    }
}
