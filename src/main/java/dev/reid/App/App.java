package dev.reid.App;

import dev.reid.doas.EmployeeDAOLocal;
import dev.reid.handlers.*;
import dev.reid.services.EmployeeService;
import dev.reid.services.EmployeeServiceImpl;
import dev.reid.services.ExpenseService;
import dev.reid.services.ExpenseServiceImpl;
import io.javalin.Javalin;

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
    public static ExpenseService expenseService = new ExpenseServiceImpl();


    public static void main(String[] args) {
        Javalin app = Javalin.create();
        CreateEmployeeHandler createEmployeeHandler = new CreateEmployeeHandler();

        DeleteEmployeeHandler deleteEmployeeHandler = new DeleteEmployeeHandler();
        GetEmployeeByIDHandler getEmployeeByIDHandler = new GetEmployeeByIDHandler();
        UpdateEmployeeByIDHandler updateEmployeeByIDHandler = new UpdateEmployeeByIDHandler();
        GetAllEmployeesHandler getAllEmployeesHandler = new GetAllEmployeesHandler();

        CreateExpenseHandler createExpenseHandler = new CreateExpenseHandler();
        DeleteExpenseHandler deleteExpenseHandler = new DeleteExpenseHandler();
        GetAllExpensesHandler getAllExpensesHandler = new GetAllExpensesHandler();
        GetExpenseByExpenseIDHandler getExpenseByExpenseIDHandler = new GetExpenseByExpenseIDHandler();
        GetExpenseByStatusHandler getExpenseByStatusHandler = new GetExpenseByStatusHandler();
        GetExpensesByEmployeeIDHandler getExpensesByEmployeeIDHandler = new GetExpensesByEmployeeIDHandler();
        UpdateExpenseStatusHandler updateExpenseStatusHandler = new UpdateExpenseStatusHandler();





        app.post("/employees", createEmployeeHandler);
        app.get("/employees", getAllEmployeesHandler);
        app.get("/employees/{id}", getEmployeeByIDHandler);
        app.put("/employees/{id}", updateEmployeeByIDHandler);
        app.delete("/employees/{id}", deleteEmployeeHandler);

        app.get("/expenses?status={status}", getExpenseByStatusHandler);//not working

        app.get("/expenses", getAllExpensesHandler);//work
        app.get("/expenses/{id}", getExpenseByExpenseIDHandler);//work
        app.put("/expenses/{id}", createExpenseHandler);//work
        app.patch("/expenses/{id}/{status}", updateExpenseStatusHandler);
        app.delete("/expenses/{id}", deleteExpenseHandler);//works
        app.get("/employees/{id}/expenses", getExpensesByEmployeeIDHandler);//works


        app.start();


    }
}
