package dev.reid.handlers;

import dev.reid.App.App;
import dev.reid.entity.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class DeleteExpenseHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        String result = App.expenseService.deleteExpense(id);
        if (result == "202")
        {
            ctx.result("The expense has been deleted");
            ctx.status(202);
        }
        else if (result == "404")
        {
            ctx.result("Expense not found");
            ctx.status(404);
        }
        else if (result == "422")
        {
            ctx.status(422);
            ctx.result("Expense is already approved or denied");
        }
        else
        {
            throw new RuntimeException("");
        }

         /*
        DELETE /expenses/19
returns a 404 if expense not found
return a 422 if expense is already approved or denied
Once approved or denied they CANNOT be deleted or edited


        */



    }
}
