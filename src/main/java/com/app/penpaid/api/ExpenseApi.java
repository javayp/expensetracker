package com.app.penpaid.api;

import com.app.penpaid.model.Expense;
import com.app.penpaid.service.ExpenseService;
import com.app.penpaid.validation.ExpenseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/expense/api")
public class ExpenseApi {

    private final ExpenseService expenseService;

    @Autowired
    public ExpenseApi(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }


    @PostMapping("/new")
    public void createExpense(@RequestBody List<Expense> expenseList) {
        ExpenseValidator.validateExpenseBody(expenseList);
        expenseService.createExpense(expenseList);
    }
}
