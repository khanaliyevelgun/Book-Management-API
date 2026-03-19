package com.elgun.dao.entity;

import com.elgun.enumm.BookAvailability;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "books")
@Entity
@Builder
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "book_availability")
    @Enumerated(EnumType.STRING)
    private BookAvailability bookAvailability;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "publish_date")
    private LocalDateTime publishDate;

    @Column(name = "book_count")
    private Long bookCount;

    @ManyToMany(mappedBy = "books")
    private List<Reservation> reservationList;
}
