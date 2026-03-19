package com.elgun.Dto;

import com.elgun.enumm.BookAvailability;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookResponseDto {
    private String bookName;
    private BigDecimal price;
    private BookAvailability bookAvailability;
    private String authorName;
    private LocalDateTime publishDate;

}
