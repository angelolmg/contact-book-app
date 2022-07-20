package com.angelogoes.contactbookapp.exceptions;

public class ContactNotFoundException extends RuntimeException{
    public ContactNotFoundException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }
    
    public ContactNotFoundException(String exMessage) {
        super(exMessage);
    }
}
