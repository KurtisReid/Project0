package dev.reid.handlers;

import com.google.gson.Gson;
import dev.reid.App.App;
import dev.reid.entity.Employee;
import dev.reid.entity.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class CreateExpenseHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        String json = ctx.body();
        Gson gson = new Gson();
        Expense expense = gson.fromJson(json, Expense.class);

        Expense registeredExpense = App.expenseService.registerExpense(expense);


        String expenseJson = gson.toJson(registeredExpense);
        ctx.status(201);
        ctx.result(expenseJson);

    }
}
