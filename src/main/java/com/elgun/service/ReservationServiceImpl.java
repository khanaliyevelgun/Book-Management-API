package com.elgun.service;
import com.elgun.dao.entity.Book;
import com.elgun.dao.entity.Reservation;
import com.elgun.dao.entity.User;
import com.elgun.dao.repository.BookRepository;
import com.elgun.dao.repository.ReservationRepository;
import com.elgun.Dto.ReservationRequestDto;
import com.elgun.Dto.ReservationResponseDto;
import com.elgun.enumm.BookAvailability;
import com.elgun.enumm.ReservationStatus;
import com.elgun.enumm.UserActive;
import com.elgun.exception.*;
import com.elgun.fetcher.EntityFetch;
import com.elgun.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.elgun.constants.AppConstants.*;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{
    private final EntityFetch entityFetch;
    private final BookRepository bookRepository;
    private final ReservationMapper reservationMapper;
    private final ReservationRepository reservationRepository;


    @Override
    @Transactional
    public void createReservation(ReservationRequestDto requestDto) {
        User user = entityFetch.fetchUserIfExists(requestDto.getUserId());
        if (user.getUserActive() != UserActive.ACTIVE){
            throw new UserNotActiveException(USER_NOT_ACTIVE);
        }
        List<Long> bookIds = requestDto.getBookIds();
        if (bookIds.size()>3){
            throw new BookCountExceeded(MAX_BOOK_LIMIT);
        }
        List<Book> books = new ArrayList<>();
        for (Long bookId : bookIds) {
            Book book = entityFetch.fetchBookIfExists(bookId);
            if (book.getBookAvailability() != BookAvailability.AVAILABLE) {
                throw new BookNotAvailableException(BOOK_NOT_AVAILABLE);
            }
            books.add(book);
        }
        for (Book book : books){
            book.setBookAvailability(BookAvailability.UNAVAILABLE);
        }
        bookRepository.saveAll(books);
            reservationRepository.save(reservationMapper.mapRequestDtoToEntity(requestDto,books,user));
    }

    @Override
    @Transactional
    public void approveReservation(Long id) {
        Reservation reservation = entityFetch.fetchReservationIfExists(id);
        if (reservation.getReservationStatus() != ReservationStatus.PENDING){
            throw new InvalidReservationStatusException(INVALID_RESERVATION_STATUS);
        }
        reservation.setReservationStatus(ReservationStatus.ACTIVE);
        reservationRepository.save(reservation);
    }

    @Override
    @Transactional
    public void cancelReservation(Long id) {
        Reservation reservation = entityFetch.fetchReservationIfExists(id);
        if (reservation.getReservationStatus()==ReservationStatus.CANCELLED){
            throw new InvalidReservationStatusException(INVALID_RESERVATION_STATUS);
        }
        reservation.setReservationStatus(ReservationStatus.CANCELLED);
        List<Book> books = reservation.getBooks();
        for (Book book : books) {
            book.setBookAvailability(BookAvailability.AVAILABLE);
        }
        bookRepository.saveAll(books);
        reservationRepository.save(reservation);
    }


    @Override
    public ReservationResponseDto getReservationById(Long id) {
        return reservationMapper.mapEntityToResponseDto(entityFetch.fetchReservationIfExists(id));
    }

    @Override
    public Page<ReservationResponseDto> getAllReservationsByUser(Long userId, int page, int size) {
        User user = entityFetch.fetchUserIfExists(userId);
        Pageable pageable = PageRequest.of(page,size);
        return reservationRepository.findByUser_Id(userId,pageable).map(reservationMapper::mapEntityToResponseDto);
    }
}


