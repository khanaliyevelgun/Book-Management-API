package com.elgun.mapper;

import com.elgun.dao.entity.Book;
import com.elgun.dao.entity.Reservation;
import com.elgun.dao.entity.User;
import com.elgun.Dto.ReservationRequestDto;
import com.elgun.Dto.ReservationResponseDto;
import com.elgun.enumm.ReservationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReservationMapper {
    private final BookMapper bookMapper;

    public Reservation mapRequestDtoToEntity(ReservationRequestDto requestDto, List<Book> books, User user) {
        return Reservation.builder()
                .reservationTime(requestDto.getReservationTime())
                .books(books)
                .reservationStatus(ReservationStatus.PENDING)
                .user(user).build();

    }

    public ReservationResponseDto mapEntityToResponseDto(Reservation reservation) {
        return ReservationResponseDto.builder()
                .id(reservation.getId())
                .reservationTime(reservation.getReservationTime())
                .books(reservation.getBooks().stream().map(bookMapper::mapEntityToResponseDto).toList())
                .reservationStatus(reservation.getReservationStatus())
                .username(reservation.getUser().getName())
                .build();

    }

}
