package com.crud.client.infrastructure.exceptions;

public class DataHasNotChangedException extends RuntimeException {

    public DataHasNotChangedException(String message) {
        super(message);
    }
}