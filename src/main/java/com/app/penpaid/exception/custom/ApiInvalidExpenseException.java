package com.app.penpaid.exception.custom;

import com.app.penpaid.exception.parent.PenPaidException;

public class ApiInvalidExpenseException extends PenPaidException {
    public ApiInvalidExpenseException(final String message, final String code){
        super(message,code);
    }

}