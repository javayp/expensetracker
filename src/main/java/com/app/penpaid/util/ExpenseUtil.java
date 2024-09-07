package com.app.penpaid.util;

import com.app.penpaid.model.Expense;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class ExpenseUtil {

    public static void modifyExpense(List<Expense> expenseList) {
        expenseList.forEach(expense -> {
                    expense.setExpenseEntryMoment(LocalDateTime.now(ZoneId.of("UTC")));
                    expense.setExpenseId(RandomKeyGeneratorUtil.generateRandomKey());
                }
        );
    }
}
