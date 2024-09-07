package com.app.penpaid.exception.custom;

import com.app.penpaid.exception.parent.PenPaidException;

public class ApiInvalidExpenseIdException extends PenPaidException {

    public ApiInvalidExpenseIdException(String message, String code) {
        super(message, code);
    }
}
