package com.elgun;

import com.elgun.Dto.ReservationResponseDto;
import com.elgun.dao.entity.Reservation;

public class ReservationMapper {
    private ReservationMapper(){}
    public static ReservationResponseDto mapReservationEntityToReservationResponseDto(Reservation reservation){
      return  ReservationResponseDto.builder().id(reservation.getId()).build();
    }
}
