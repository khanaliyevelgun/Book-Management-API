package com.elgun.controller;

import com.elgun.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.elgun.constants.AppConstants.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BookNotAvailableException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse handleBookNotAvailableException(BookNotAvailableException ex){
        return new ExceptionResponse(BOOK_NOT_AVAILABLE, ex.getMessage());
    }
    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleBookNotFoundException(BookNotFoundException ex){
        return new ExceptionResponse(BOOK_NOT_FOUND, ex.getMessage());
    }
    @ExceptionHandler(ReservationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleReservationNotFoundException(ReservationNotFoundException ex){
        return new ExceptionResponse(RESERVATION_NOT_FOUND, ex.getMessage());
    }
    @ExceptionHandler(BookCountExceeded.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleBookCountExceededException(BookCountExceeded ex){
        return new ExceptionResponse(MAX_BOOK_LIMIT, ex.getMessage());
    }
    @ExceptionHandler(InvalidReservationStatusException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse handleInvalidReservationStatusException(InvalidReservationStatusException ex){
        return new ExceptionResponse(INVALID_RESERVATION_STATUS, ex.getMessage());
    }
    @ExceptionHandler(UserNotActiveException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionResponse handleUserNotActiveException(UserNotActiveException ex){
        return new ExceptionResponse(USER_NOT_ACTIVE, ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleUserNotFoundException(UserNotFoundException ex){
        return new ExceptionResponse(USER_NOT_FOUND, ex.getMessage());
    }




}

