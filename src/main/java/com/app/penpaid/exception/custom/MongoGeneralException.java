package com.app.penpaid.exception.custom;

import com.app.penpaid.exception.parent.PenPaidException;

public class MongoGeneralException extends PenPaidException {

    public MongoGeneralException(final String message, final String code){
        super(message,code);
    }
}
