package com.getir.readingisgood.exception;

public class BookIsNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public BookIsNotFoundException(String msg) {
        super(msg);
    }
}
