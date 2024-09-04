package com.app.penpaid.validation;

import com.app.penpaid.exception.custom.InvalidExpense;
import com.app.penpaid.model.Expense;

import java.util.List;

public class ExpenseValidator {


    public static void validateExpenseBody(List<Expense> expenseList) {
        if (expenseList == null || expenseList.isEmpty()) {
            throw new InvalidExpense("Expense body is empty", "400");
        }else if (expenseList.get(0).getExpenseAmount() <= 0.0) {
            throw new InvalidExpense("Expense amount cannot be negative", "400");
        }

    }
}
