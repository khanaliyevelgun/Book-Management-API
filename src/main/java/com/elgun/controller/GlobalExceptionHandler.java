package com.elgun.controller;

import com.elgun.exception.BookIsNotAvailableException;
import com.elgun.exception.BookNotFoundException;
import com.elgun.exception.ExceptionResponse;
import com.elgun.exception.ReservationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.elgun.constants.constants.*;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BookIsNotAvailableException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse handleBookIsNotAvailableException(BookIsNotAvailableException ex){
        return new ExceptionResponse(BOOK_IS_NOT_AVAILABLE, ex.getMessage());
    }
    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleBookNotFoundException(BookNotFoundException ex){
        return new ExceptionResponse(BOOK_IS_NOT_FOUND, ex.getMessage());
    }
    @ExceptionHandler(ReservationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleReservationNotFoundException(ReservationNotFoundException ex){
        return new ExceptionResponse(RESERVATION_IS_NOT_FOUND, ex.getMessage());
    }

}

