package dev.reid.handlers;

import com.google.gson.Gson;
import dev.reid.App.App;
import dev.reid.entity.Expense;
import dev.reid.entity.Status;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class GetExpenseByStatusHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        System.out.println("hello its me");
        String s1 = String.format(ctx.queryParam("status"));
        Gson gson = new Gson();
        Map<Integer, Expense> expense = new HashMap();
        System.out.println("hello its me");
        System.out.println(s1);
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

        String json = gson.toJson(expense);
        ctx.result(json);



    }
}
