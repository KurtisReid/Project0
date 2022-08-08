package dev.reid.handlers;

import com.google.gson.Gson;
import dev.reid.App.App;
import dev.reid.entity.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class UpdateExpenseByEmployeeHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        System.out.println("???????????");
        int employeeid = Integer.parseInt(ctx.pathParam("id"));
        System.out.println(employeeid);
        String expenseJson = ctx.body();
        Gson gson = new Gson();


        Expense expense = gson.fromJson(expenseJson, Expense.class);
        System.out.println(expense);
        expense.setEmployeeIssuer(employeeid);
        System.out.println(expense);
        Expense updatedExpense = App.expenseService.modifyExpense(expense);
        String json = gson.toJson(updatedExpense);
        System.out.println("updated expense: " + updatedExpense);
        ctx.result(json);
    }
}
