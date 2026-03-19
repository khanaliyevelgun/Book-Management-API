package com.elgun.Dto;

import com.elgun.enumm.ReservationStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ReservationResponseDto {
    private Long id;
    private LocalDateTime reservationTime;
    private List<BookResponseDto> books;
    private ReservationStatus reservationStatus;
    private String username;

}
