package dev.reid.handlers;

import com.google.gson.Gson;
import dev.reid.entity.App.App;
import dev.reid.entity.Employee;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class GetEmployeeByIDHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));

        Employee employee = App.employeeService.retriveRemployeeByID(id);
        Gson gson = new Gson();
        String json = gson.toJson(employee);
        ctx.result(json);

    }
}
