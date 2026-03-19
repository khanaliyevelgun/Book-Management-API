package com.elgun.fetcher;

import com.elgun.dao.entity.Book;
import com.elgun.dao.entity.Reservation;
import com.elgun.dao.entity.User;
import com.elgun.dao.repository.BookRepository;
import com.elgun.dao.repository.ReservationRepository;
import com.elgun.dao.repository.UserRepository;
import com.elgun.exception.BookNotFoundException;
import com.elgun.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import static com.elgun.constants.AppConstants.*;

@Component
@RequiredArgsConstructor
public class EntityFetch {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    public Book fetchBookIfExists(Long id){
        return bookRepository.findById(id).orElseThrow(()->new BookNotFoundException(BOOK_NOT_FOUND + id));
    }
    public User fetchUserIfExists(Long id){
        return userRepository.findById(id).orElseThrow(()->new UserNotFoundException(USER_NOT_FOUND + id));
    }
    public Reservation fetchReservationIfExists(Long id){
        return reservationRepository.findById(id).orElseThrow(()->new UserNotFoundException(RESERVATION_NOT_FOUND + id));
    }
}
