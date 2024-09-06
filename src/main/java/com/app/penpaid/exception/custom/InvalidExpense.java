package com.app.penpaid.exception.custom;

import com.app.penpaid.exception.parent.PenPaidException;

public class InvalidExpense extends PenPaidException {


    public InvalidExpense(final String message,final String code){
        super(message,code);
    }

}