package dev.reid.doaTests;

import dev.reid.doas.ExpenseDOA;
import dev.reid.doas.ExpenseDOALocal;
import dev.reid.entity.Expense;
import dev.reid.entity.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExpenseDOATests {
    static ExpenseDOA expenseDOA = new ExpenseDOALocal();

    @Test
    void create_expense_test()
    {
        Expense expense = new Expense(1,2.00, Status.PENDING, 1);
        Expense savedExpense = expenseDOA.createExpense(expense);
        Assertions.assertNotEquals(0, savedExpense.getId());


    }
}
