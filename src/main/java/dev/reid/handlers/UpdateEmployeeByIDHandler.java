package dev.reid.handlers;

import com.google.gson.Gson;
import dev.reid.entity.App.App;
import dev.reid.entity.Employee;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class UpdateEmployeeByIDHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        String employeeJson = ctx.body();
        Gson gson = new Gson();

        Employee employee = gson.fromJson(employeeJson, Employee.class);
        App.employeeService.modifyEmployee(employee);
        Employee updatedEmployee = App.employeeService.modifyEmployee(employee);

        String json = gson.toJson(updatedEmployee);
        ctx.result(json);

    }
}
