package com.app.penpaid.exception.custom;

import com.app.penpaid.exception.parent.PenPaidException;

public class ApiInternalServerException extends PenPaidException {

    public ApiInternalServerException(String message, String code) {
        super(message, code);
    }
}
