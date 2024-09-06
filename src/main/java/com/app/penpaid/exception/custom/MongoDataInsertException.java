package com.app.penpaid.exception.custom;

import com.app.penpaid.exception.parent.PenPaidException;

public class MongoDataInsertException extends PenPaidException {

    public MongoDataInsertException(final String message, final String code) {
        super(message, code);
    }
}
