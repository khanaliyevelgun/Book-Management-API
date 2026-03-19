package com.elgun.Dto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationRequestDto {

    @NotNull
    private LocalDateTime reservationTime;

    @NotNull
    private List<Long> bookIds;

    @NotNull
    private Long userId;


}
