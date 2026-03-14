package com.elgun.exception;

public class BookIsNotAvailableException extends RuntimeException {
    public BookIsNotAvailableException(String message) {
        super(message);
    }
}
