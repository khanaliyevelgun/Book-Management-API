package com.elgun.Dto;

import com.elgun.dao.entity.UserActive;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationRequestDto {

    @NotNull
    private Long bookId;

    @NotNull
    private Long userId;

    @NotNull
    private UserActive userActive;

}
