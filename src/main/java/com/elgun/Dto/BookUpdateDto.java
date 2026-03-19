package com.elgun.Dto;

import com.elgun.enumm.BookAvailability;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class BookUpdateDto {

    private String bookName;

    @PositiveOrZero
    private BigDecimal price;

    private Long bookCount;

    private String authorName;

    private LocalDateTime publishDate;

    private BookAvailability bookAvailability;
}
