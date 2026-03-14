package com.elgun.dao.repository;

import com.elgun.dao.entity.Reservation;
import com.elgun.dao.entity.UserActive;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> findByUserIdAndUserActive(Long userId, UserActive active);

}
