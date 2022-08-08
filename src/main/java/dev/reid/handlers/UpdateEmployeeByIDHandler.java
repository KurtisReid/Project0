package dev.reid.handlers;

import com.google.gson.Gson;
import dev.reid.App.App;
import dev.reid.entity.Employee;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class UpdateEmployeeByIDHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        int id = Integer.parseInt(ctx.pathParam("id"));

        Employee employee = App.employeeService.retriveRemployeeByID(id);

        String employeeJson = ctx.body();
        Gson gson = new Gson();

        Employee employee2 = gson.fromJson(employeeJson, Employee.class);
        employee2.setId(id);
        //App.employeeService.modifyEmployee(employee);
        //System.out.println(employee.getName());
       // System.out.println(employee);
        //System.out.println(employee2);
        Employee updatedEmployee = App.employeeService.modifyEmployee(employee2);
        //System.out.println(updatedEmployee);
        String json = gson.toJson(updatedEmployee);
        if (employee != null)
        {
            ctx.result(json);
        }
        else
        {
            ctx.status(404);
            ctx.result("Request failed. Employee ID not found");
        }



    }
}
