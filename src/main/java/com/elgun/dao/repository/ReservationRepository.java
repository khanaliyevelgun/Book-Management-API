package com.elgun.dao.repository;

import com.elgun.dao.entity.Reservation;
import com.elgun.enumm.ReservationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> findByReservationStatusAndReservationTimeBefore(ReservationStatus reservationStatus, LocalDateTime reservationTime);
    Page<Reservation> findByUser_Id(Long userId, Pageable pageable);
}
