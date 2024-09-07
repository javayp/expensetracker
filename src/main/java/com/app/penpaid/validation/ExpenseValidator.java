package com.app.penpaid.validation;

import com.app.penpaid.exception.custom.ApiInvalidExpenseException;
import com.app.penpaid.exception.custom.ApiInvalidExpenseIdException;
import com.app.penpaid.model.Expense;
import com.app.penpaid.util.PropertiesReaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Component
public class ExpenseValidator {

    private final PropertiesReaderUtil propertiesReaderUtil;

    @Autowired
    public ExpenseValidator(PropertiesReaderUtil propertiesReaderUtil) {
        this.propertiesReaderUtil = propertiesReaderUtil;
    }

    public  void validateExpenseBody(List<Expense> expenseList) {
        checkForEmptyBody(expenseList);
        checkForNegativeAmount(expenseList);
    }

    private void checkForNegativeAmount(List<Expense> expenseList){
        expenseList.forEach(expense -> {
            if (expense.getExpenseAmount() <= 0.0) {
                throw new ApiInvalidExpenseException(propertiesReaderUtil.getValue("A_001"), "A_001");
            }
        });
    }

    private void checkForEmptyBody(List<Expense> expenseList){
        if (CollectionUtils.isEmpty(expenseList)) {
            throw new ApiInvalidExpenseException(propertiesReaderUtil.getValue("A_002"), "A_002");
        }else {
            expenseList.forEach(expense -> {
                if (isEmptyExpense(expense)) {
                    throw new ApiInvalidExpenseException(propertiesReaderUtil.getValue("A_002"), "A_002");
                }
            });
        }
    }

    private boolean isEmptyExpense(Expense expense) {
        return Objects.isNull(expense.getExpenseId()) &&
                Objects.isNull(expense.getExpenseDate()) &&
                Double.compare(expense.getExpenseAmount(), 0.0) == 0 &&
                CollectionUtils.isEmpty(expense.getExpenseTags()) &&
                Objects.isNull(expense.getExpenseDescription()) &&
                Objects.isNull(expense.getExpenseEntryMoment());
    }

    public void validateExpenseId(String expenseId) {
        if (expenseId.length() != 15) {
            throw new ApiInvalidExpenseIdException(propertiesReaderUtil.getValue("A_003"), "A_003");
        }
    }
}
