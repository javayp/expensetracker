package com.app.penpaid.exception.custom;

import com.app.penpaid.exception.parent.PenPaidException;

public class MongoDocumentNotFoundException extends PenPaidException {

    public MongoDocumentNotFoundException(String message, String errorCode) {
        super(message, errorCode);
    }
}
