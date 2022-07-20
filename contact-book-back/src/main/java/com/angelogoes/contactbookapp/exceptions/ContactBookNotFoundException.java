package com.angelogoes.contactbookapp.exceptions;

public class ContactBookNotFoundException extends RuntimeException{
    public ContactBookNotFoundException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }
    
    public ContactBookNotFoundException(String exMessage) {
        super(exMessage);
    }
}
