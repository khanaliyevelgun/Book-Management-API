package com.elgun.service;


import com.elgun.Dto.ReservationRequestDto;
import com.elgun.Dto.ReservationResponseDto;
import org.springframework.data.domain.Page;

public interface ReservationService {
    void createReservation(ReservationRequestDto requestDto);
    void approveReservation(Long id);
    void cancelReservation(Long id);
    ReservationResponseDto getReservationById(Long id);
    Page<ReservationResponseDto> getAllReservationsByUser(Long userId,int page,int size);


}
