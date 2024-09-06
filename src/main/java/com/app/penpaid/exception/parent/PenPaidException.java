package com.app.penpaid.exception.parent;

import lombok.Getter;

@Getter
public class PenPaidException extends RuntimeException {

    private final String code;

    public PenPaidException(final String message, final String code){
        super(message);
        this.code=code;
    }
}
