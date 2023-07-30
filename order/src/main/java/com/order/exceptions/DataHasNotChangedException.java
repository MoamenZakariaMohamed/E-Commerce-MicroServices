package com.order.exceptions;

public class DataHasNotChangedException extends RuntimeException {

    public DataHasNotChangedException(String message) {
        super(message);
    }
}