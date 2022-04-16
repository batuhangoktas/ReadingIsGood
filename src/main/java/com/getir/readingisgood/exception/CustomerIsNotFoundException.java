package com.getir.readingisgood.exception;

public class CustomerIsNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public CustomerIsNotFoundException(String msg) {
        super(msg);
    }
}