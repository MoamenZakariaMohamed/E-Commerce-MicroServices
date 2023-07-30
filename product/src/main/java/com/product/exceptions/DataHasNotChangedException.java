package com.product.exceptions;

public class DataHasNotChangedException extends RuntimeException {

    public DataHasNotChangedException(String message) {
        super(message);
    }
}