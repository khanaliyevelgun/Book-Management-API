package com.elgun.scheduler;

import com.elgun.dao.entity.Book;
import com.elgun.dao.entity.Reservation;
import com.elgun.dao.repository.BookRepository;
import com.elgun.dao.repository.ReservationRepository;
import com.elgun.enumm.BookAvailability;
import com.elgun.enumm.ReservationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor

public class ReservationScheduler {
    private final ReservationRepository reservationRepository;
    private final BookRepository bookRepository;
    @Scheduled(fixedRate = 60000)
    public void handleReservationRequests(){
        List<Reservation> reservations = reservationRepository.findByReservationStatusAndReservationTimeBefore(ReservationStatus.PENDING,LocalDateTime.now().minusHours(2));
        for (Reservation reservation : reservations) {
            reservation.setReservationStatus(ReservationStatus.CANCELLED);
            List<Book> books = reservation.getBooks();
            for (Book book : books) {
                book.setBookAvailability(BookAvailability.AVAILABLE);
            }
            bookRepository.saveAll(books);

        }
        reservationRepository.saveAll(reservations);


    }
}
