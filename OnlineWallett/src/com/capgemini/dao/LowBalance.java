package com.capgemini.dao;

@SuppressWarnings("serial")
public class LowBalance extends RuntimeException{

    public LowBalance(final String msg){
        super(msg);
    }

    public LowBalance(final String msg,final Throwable e){
        super(msg,e);
    }
}