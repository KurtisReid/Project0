package dev.reid.handlers;

import com.google.gson.Gson;
import dev.reid.App.App;
import dev.reid.entity.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class GetExpensesByEmployeeIDHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int employeeId = Integer.parseInt(ctx.pathParam("id"));

        Map<Integer, Expense> expense = App.expenseService.getExpenseByEmployee(employeeId);

        Gson gson = new Gson();
        String json = gson.toJson(expense);
        ctx.result(json);


    }
}
