CREATE TABLE IF NOT EXISTS reservations (
                                            id               BIGSERIAL PRIMARY KEY,
                                            reservation_date TIMESTAMP NOT NULL,
                                            status           VARCHAR(50) NOT NULL,
    user_id          BIGINT NOT NULL,
    CONSTRAINT fk_reservation_user FOREIGN KEY (user_id) REFERENCES users(id)
    );
