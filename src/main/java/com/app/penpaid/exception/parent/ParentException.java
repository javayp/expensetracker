package com.app.penpaid.exception.parent;

import lombok.Getter;

@Getter
public class ParentException extends RuntimeException {

    private String code;

    public ParentException(final String message){
        super(message);
    }

    public ParentException(final String message,final String code){
        super(message);
        this.code=code;
    }

    public ParentException(final String message, final Throwable cause){
        super(message,cause);
    }
}
