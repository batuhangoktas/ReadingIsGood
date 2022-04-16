package com.getir.readingisgood.exception;

public class CustomerIsAlreadyRegisteredException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public CustomerIsAlreadyRegisteredException(String msg) {
        super(msg);
    }
}