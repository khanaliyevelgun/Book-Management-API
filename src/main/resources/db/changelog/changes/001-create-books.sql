CREATE TABLE IF NOT EXISTS books (
                                     id          BIGSERIAL PRIMARY KEY,
                                     book_name   VARCHAR(255) NOT NULL,
    price       NUMERIC(10,2) NOT NULL,
    book_availability VARCHAR(50) NOT NULL,
    author_name VARCHAR(255) NOT NULL,
    publish_date TIMESTAMP,
    book_count  BIGINT NOT NULL
    );