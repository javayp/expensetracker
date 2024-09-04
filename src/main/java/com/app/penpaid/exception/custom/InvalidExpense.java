package com.app.penpaid.exception.custom;

import com.app.penpaid.exception.parent.ParentException;

public class InvalidExpense extends ParentException {


    public InvalidExpense(final String message,final String code){
        super(message,code);
    }

}