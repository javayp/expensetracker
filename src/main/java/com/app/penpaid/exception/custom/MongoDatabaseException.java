package com.app.penpaid.exception.custom;

import com.app.penpaid.exception.parent.PenPaidException;

public class MongoDatabaseException extends PenPaidException {
    public MongoDatabaseException(String message, String code) {
        super(message, code);
    }
}
