package com.app.penpaid.exception.custom;

import com.app.penpaid.exception.parent.PenPaidException;

public class MongoInvalidExpenseIdException extends PenPaidException {
        public MongoInvalidExpenseIdException(String message, String code) {
        super(message, code);
    }
}
