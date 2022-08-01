package dev.reid.handlers;

import com.google.gson.Gson;
import dev.reid.entity.App.App;
import dev.reid.entity.Employee;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class CreateEmployeeHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        String json = ctx.body();
        Gson gson = new Gson();
        Employee employee = gson.fromJson(json, Employee.class);
        Employee registeredEmployee = App.employeeService.registerEmployee(employee);

        String employeeJson = gson.toJson(registeredEmployee);
        ctx.status(201);
        ctx.result(employeeJson);
    }
}
