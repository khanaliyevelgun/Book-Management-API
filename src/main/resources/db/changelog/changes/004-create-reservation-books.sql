CREATE TABLE IF NOT EXISTS reservation_books (
                                                 reservation_id BIGINT NOT NULL,
                                                 book_id        BIGINT NOT NULL,
                                                 PRIMARY KEY (reservation_id, book_id),
    CONSTRAINT fk_rb_reservation FOREIGN KEY (reservation_id) REFERENCES reservations(id),
    CONSTRAINT fk_rb_book FOREIGN KEY (book_id) REFERENCES books(id)
    );