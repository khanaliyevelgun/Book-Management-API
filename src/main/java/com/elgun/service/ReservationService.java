package com.elgun.service;

import com.elgun.Dto.ReservationRequestDto;
import com.elgun.Dto.ReservationResponseDto;
import com.elgun.dao.entity.Reservation;
import com.elgun.dao.entity.UserActive;

import java.util.List;

public interface ReservationService {
    public void createReservation(ReservationRequestDto reservationRequestDto);
    public ReservationResponseDto findReservationById(Long id);
    public void approveReservation(ReservationRequestDto reservationRequestDto);
}
