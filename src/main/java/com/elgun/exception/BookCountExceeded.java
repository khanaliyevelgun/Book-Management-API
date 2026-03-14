package com.elgun.exception;

public class BookCountExceeded extends RuntimeException {
    public BookCountExceeded(String message) {
        super(message);
    }
}
