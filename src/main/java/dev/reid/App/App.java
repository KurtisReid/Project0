package dev.reid.App;

import com.google.gson.Gson;
import dev.reid.doas.EmployeeDAOLocal;
import dev.reid.doas.EmployeeDAOPostgres;
import dev.reid.doas.ExpenseDAOPostgres;
import dev.reid.entity.Expense;
import dev.reid.entity.Status;
import dev.reid.handlers.*;
import dev.reid.services.EmployeeService;
import dev.reid.services.EmployeeServiceImpl;
import dev.reid.services.ExpenseService;
import dev.reid.services.ExpenseServiceImpl;
import io.javalin.Javalin;
import io.javalin.http.Handler;

import java.util.List;
import java.util.Map;

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

    public static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDAOPostgres());
    public static ExpenseService expenseService = new ExpenseServiceImpl(new ExpenseDAOPostgres());


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

        /*
        if (s1.toUpperCase() == Status.APPROVED.name())
        {
            expense = App.expenseService.getExpensesByStatus(Status.APPROVED);

        }
        if (s1.toUpperCase() == Status.PENDING.name())
        {
            expense = App.expenseService.getExpensesByStatus(Status.PENDING);
        }
        if (s1.toUpperCase() == Status.DENIED.name())
        {
            expense = App.expenseService.getExpensesByStatus(Status.DENIED);
        }
        else
        {
            ctx.result("no status specified");
            ctx.status(404);
            return;
        }

         */

        Handler getExpensesByStatus = ctx-> {
            String status = ctx.queryParam("status");
            Gson gson = new Gson();
            if (status != null)
            {
                if (status.toUpperCase() == Status.APPROVED.name())
                {
                    Map<Integer, Expense> expenses = App.expenseService.getExpensesByStatus(Status.APPROVED);
                    String json = gson.toJson(expenses);
                    ctx.result(json);
                }
                if (status.toUpperCase() == Status.PENDING.name())
                {
                    Map<Integer, Expense> expenses = App.expenseService.getExpensesByStatus(Status.PENDING);
                    String json = gson.toJson(expenses);
                    ctx.result(json);

                }
                if (status.toUpperCase() == Status.DENIED.name())
                {
                    Map<Integer, Expense> expenses = App.expenseService.getExpensesByStatus(Status.DENIED);
                    String json = gson.toJson(expenses);
                    ctx.result(json);
                }


            }
            else
            {
                Map<Integer, Expense> books = App.expenseService.returnAllExpenses();
                String json = gson.toJson(books);
                ctx.result(json);
            }
        };



        app.get("/expenses?status={status}", getExpensesByStatus);//not working

        app.get("/expenses", getAllExpensesHandler);//work
        app.get("/expenses/{id}", getExpenseByExpenseIDHandler);//work
        app.put("/expenses/{id}", createExpenseHandler);//work
        app.patch("/expenses/{id}/{status}", updateExpenseStatusHandler);
        app.delete("/expenses/{id}", deleteExpenseHandler);//works
        app.get("/employees/{id}/expenses", getExpensesByEmployeeIDHandler);//works


        app.start();


    }
}
