package com.capgemini.dao;

@SuppressWarnings("serial")
public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException(final String msg){
        super(msg);
    }

    public AccountNotFoundException(final String msg,final Throwable e){
        super(msg,e);
    }
}
