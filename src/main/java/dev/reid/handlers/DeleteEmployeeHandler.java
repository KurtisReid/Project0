package dev.reid.handlers;

import dev.reid.entity.App.App;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class DeleteEmployeeHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean result = App.employeeService.deleteEmployee(id);
        if (result)
        {
            ctx.result("The employee has been terminated");
        }
        else
        {
            ctx.status(400);
            ctx.result("Unable to terminate the employee");
        }
    }
}
