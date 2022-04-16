package com.getir.readingisgood.exception;

public class StockIsNotEnoughException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public StockIsNotEnoughException(String msg) {
        super(msg);
    }
}