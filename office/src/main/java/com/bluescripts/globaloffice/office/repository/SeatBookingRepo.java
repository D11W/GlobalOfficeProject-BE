package com.bluescripts.globaloffice.office.repository;

import com.bluescripts.globaloffice.office.entity.SeatBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeatBookingRepo extends JpaRepository<SeatBooking,String> {

    Optional<SeatBooking> findBySeatBookingId(String seatBookingId);

}
