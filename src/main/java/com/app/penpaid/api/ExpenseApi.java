package com.app.penpaid.api;

import com.app.penpaid.model.Expense;
import com.app.penpaid.service.ExpenseService;
import com.app.penpaid.validation.ExpenseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/expense/")
public class ExpenseApi {

    private final ExpenseService expenseService;
    private final ExpenseValidator expenseValidator;

    @Autowired
    public ExpenseApi(ExpenseService expenseService, ExpenseValidator expenseValidator) {
        this.expenseService = expenseService;
        this.expenseValidator = expenseValidator;
    }

    @PostMapping("/new")
    public ResponseEntity<Expense> createExpense(@RequestBody List<Expense> expenseList) {
        expenseValidator.validateExpenseBody(expenseList);
        boolean isCreated = expenseService.createExpense(expenseList);
        return new ResponseEntity<>(isCreated ? CREATED : INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/get/{expenseId}")
    public ResponseEntity<Expense> retrieveExpense(@PathVariable("expenseId") String expenseId) {
        expenseValidator.validateExpenseId(expenseId);
        return new ResponseEntity<>(expenseService.retriveExpense(expenseId), OK);
    }

    @DeleteMapping("/delete/{expenseId}")
    public ResponseEntity<?> deleteExpenseId(@PathVariable("expenseId") String expenseId) {
        expenseValidator.validateExpenseId(expenseId);
        boolean isDeleted = expenseService.deleteExpense(expenseId);
        return new ResponseEntity<>(isDeleted ? NO_CONTENT : INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/latest")
    public ResponseEntity<List<Expense>> retrieveLatestExpense() {
        return new ResponseEntity<>(expenseService.getLatestExpense(), OK);
    }

}
