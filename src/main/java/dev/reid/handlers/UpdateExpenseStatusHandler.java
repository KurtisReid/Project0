package dev.reid.handlers;

import com.google.gson.Gson;
import dev.reid.App.App;
import dev.reid.entity.Employee;
import dev.reid.entity.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class UpdateExpenseStatusHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int employeeid = Integer.parseInt(ctx.pathParam("id"));
        String status = ctx.pathParam("status");

        String expenseJson = ctx.body();
        Gson gson = new Gson();
        System.out.println("UpdateExpenseStatusHandler");

        Expense expense = gson.fromJson(expenseJson, Expense.class);
        expense.setStatus(status);
        expense.setEmployeeIssuer(employeeid);
        Expense updatedExpense = App.expenseService.modifyExpense(expense);

        String json = gson.toJson(updatedExpense);
        ctx.result(json);

    }
}
