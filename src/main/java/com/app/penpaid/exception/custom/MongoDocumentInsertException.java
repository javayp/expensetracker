package com.app.penpaid.exception.custom;

import com.app.penpaid.exception.parent.PenPaidException;

public class MongoDocumentInsertException extends PenPaidException {

    public MongoDocumentInsertException(final String message, final String code) {
        super(message, code);
    }
}
