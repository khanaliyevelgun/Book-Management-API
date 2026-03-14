package com.elgun.service;

import com.elgun.Dto.ReservationRequestDto;
import com.elgun.Dto.ReservationResponseDto;
import com.elgun.dao.entity.*;
import com.elgun.dao.repository.BookRepository;
import com.elgun.dao.repository.ReservationRepository;
import com.elgun.dao.repository.UserRepository;
import com.elgun.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

import static com.elgun.ReservationMapper.mapReservationEntityToReservationResponseDto;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{
    private final ReservationRepository reservationRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Override
    public void createReservation(ReservationRequestDto reservationRequestDto) {
        Book book = fetchBookIfExists(reservationRequestDto.getBookId());
       Integer count = findByUserIdAndUserActive(reservationRequestDto.getUserId(),UserActive.ACTIVE);
        if (count<=3){
            throw new BookCountExceeded("Book count exceeded 3");
        }

        if (book.getBookAvailability()==BookAvailability.AVAILABLE){
            reservationRepository.save(Reservation.builder().bookId(book.getId())
                    .time(LocalDateTime.now()).build());
        }
        else throw new BookIsNotAvailableException("Book is not available in stock");

    }


    @Override
    public ReservationResponseDto findReservationById(Long id) {
        return mapReservationEntityToReservationResponseDto(fetchReservationIfExists(id));
    }

    @Override
    @Scheduled(fixedRate = 7200000)
    public void approveReservation(ReservationRequestDto reservationRequestDto) {
        User user = fetchUserIfExists(reservationRequestDto.getUserId());
        if (user.getUserRole()!=UserRole.ADMIN){
            throw new UserIsNotAdminException("User is not admin");
        }
        userRepository.save(user);
    }



    private Book fetchBookIfExists(Long id){
        return bookRepository.findById(id).orElseThrow(()->new BookNotFoundException("Book is not found"));
    }
    private Reservation fetchReservationIfExists(Long id){
        return reservationRepository.findById(id).orElseThrow(()->new ReservationNotFoundException("Reservation is not found"));
    }
    private Integer findByUserIdAndUserActive(Long userId, UserActive active){
       List<Reservation> reservations = reservationRepository.findByUserIdAndUserActive(userId,active);
       Integer count = reservations.size();
       return count;

    }
    private User fetchUserIfExists(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User is not found"));
        return user;

    }

}
